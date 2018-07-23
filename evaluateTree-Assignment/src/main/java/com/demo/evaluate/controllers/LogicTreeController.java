package com.demo.evaluate.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.demo.evaluate.controllers.validator.Language;
import com.demo.evaluate.domain.LogicTree;
import com.demo.evaluate.kryo.MediaType;
import com.demo.evaluate.service.LogicTreeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import lombok.NoArgsConstructor;

@Api(tags = {"logic-tree"})
@RestController
@Path("/")
@NoArgsConstructor
@SwaggerDefinition(
        info = @Info(contact = @Contact(name = "Pralabh Garg"),
                title = "Evaluate tree Service", version = "1.0"),
        schemes = {SwaggerDefinition.Scheme.HTTP}
)
public class LogicTreeController {
    private LogicTreeService logicTreeService;

    @Context
    private UriInfo uriInfo;

    /**
     * Using Spring constructor injection
     *
     * @param logicTreeService LogicTreeService
     * @param optionService    OptionService
     */
    @Autowired
    public LogicTreeController(LogicTreeService logicTreeService) {
        this.logicTreeService = logicTreeService;
    }

    @ApiOperation(value = "Gets a LogicTree.", response = LogicTree.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Incorrect Path Parameter."),
            @ApiResponse(code = 404, message = "LogicTree not found."),
            @ApiResponse(code = 405, message = "Method Not Allowed"),
            @ApiResponse(code = 406, message = "Media Type Not Accepted.")
    })
    @GET
    @Produces({MediaType.APPLICATION_KRYO, MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("{lngcode}/logic/tree/models/{code}/lowerLevelPackages/{package}")
    public Response getLogicTreeByteArray(
            @ApiParam(value = "The requested Language for the Logic Tree.", required = true)
            @Language @PathParam("lngcode") String lngcode,
            @ApiParam(value = "The consumer code which represents a unique identifier of a model.", required = true)
            @PathParam("code") String code,
            @ApiParam(value = "The lower level package assigned to help identify a specific model.", required = true)
            @PathParam("package") String pack) {

        List<LogicTree> logicTree =
                this.logicTreeService.findByCcodeAndLlpCodeAndLanguageCode(code, pack, lngcode);

        if (logicTree != null && !logicTree.isEmpty()) {
            System.out.println("\n\n\n\n"+logicTree);
            return Response.ok(logicTree.get(0)).build();
            
        } else {
            throw new NotFoundException("LogicTree not found.");
        }
    }

}
