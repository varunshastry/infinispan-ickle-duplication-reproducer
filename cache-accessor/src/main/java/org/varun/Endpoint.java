package org.varun;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.varun.entity.QuestionQuestion;
import org.varun.entity.QuestionQuestionKey;
import org.varun.utils.CacheUtil;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/cache")
public class Endpoint {

    @Inject
    CacheUtil cacheUtil;

    @GET
    @Path("/getquestionquestion/{realm_sourcecode_targetcode}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getQuestionQuestion(@PathParam("realm_sourcecode_targetcode") final String realmSourceCodeTargetCode) throws JsonProcessingException {
        String[] tokens = StringUtils.split(realmSourceCodeTargetCode, Constants.COMMA);
        if (tokens.length != 3) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("The input parameter format should be in the form of \"realm" + Constants.COMMA + "sourcecode" + Constants.COMMA + "targetcode\"")
                    .build();
        }
        QuestionQuestionKey key = new QuestionQuestionKey(tokens[0], tokens[1], tokens[2]);
        QuestionQuestion questionQuestion = cacheUtil.getQuestionQuestion(key);
        String response;
        if (questionQuestion != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.writeValueAsString(questionQuestion);
        } else {
            response = Constants.QUESTIONQUESTION_NOT_FOUND_IN_CACHE;
        }
        return Response.ok()
                .entity(response)
                .build();
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    @Path("/postquestionquestion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postQuestionQuestion(QuestionQuestion questionQuestion) {
        QuestionQuestionKey key = cacheUtil.saveQuestionQuestion(questionQuestion);
        return Response.ok()
                .entity(key)
                .build();
    }

    @PUT
    @Path("/put")
    @Produces(MediaType.TEXT_PLAIN)
    public Response put() {
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}