package com.daemongear.trackit.api;

import com.google.common.net.MediaType;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.ServerSocket;

/**
 * Created by robertoosorio on 15-08-16.
 */
@RunWith(VertxUnitRunner.class)
public class MainVerticleTest {
    private Vertx vertx;
    int port;
    @Before
    public void setUp(TestContext context) {
        vertx = Vertx.vertx();
        port = 8081;
        DeploymentOptions options = new DeploymentOptions()
                .setConfig(new JsonObject().put("http.port", port));
        vertx.deployVerticle(MainVerticle.class.getName(), options, context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testMainApplication(TestContext context) {
        System.out.println("testMainApplication");
        final Async async = context.async();

        vertx.createHttpClient().getNow(port,"localhost","/", response -> {
            response.handler(body -> {
                System.out.println(String.format("Response: %s", body.toString()));
                context.assertTrue(body.toString().contains("Hello"));
                async.complete();
            });
        });
    }

    @Test
    public void registerUserTest(TestContext context) {
        System.out.println("registerUserTest");
        final Async async = context.async();

        JsonObject jsonObject = new JsonObject();
        jsonObject.put("username","roberto@daemongear.com");
        jsonObject.put("password", "123123");

        System.out.println(String.format("Content-Type: %s", MediaType.JSON_UTF_8.toString()));

        System.out.println(String.format("Sending json : %s", jsonObject.encodePrettily()));
        vertx
                .createHttpClient()
                .post(port, "localhost", "/auth/register",
                        response -> {
                            response.handler(body -> {
                                System.out.println(String.format("Response: %s", body.toString()));
                                context.assertTrue(body.toString().contains("roberto@daemongear.com"));
                                async.complete();
                            }
                            );
                        }
                )
                .putHeader(HttpHeaders.CONTENT_LENGTH, Buffer.buffer(jsonObject.encode()).length() + "")
                .putHeader(HttpHeaders.CONTENT_TYPE, MediaType.JSON_UTF_8.toString())
                .write(jsonObject.encode())
                .end();

    }
}
