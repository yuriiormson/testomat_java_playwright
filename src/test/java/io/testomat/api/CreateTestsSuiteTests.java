package io.testomat.api;

import io.testomat.api.controllers.AuthController;
import io.testomat.api.controllers.ProjectController;
import io.testomat.api.controllers.SuitesController;
import io.testomat.api.dtos.Attributes;
import io.testomat.api.dtos.DataItem;
import io.testomat.api.dtos.ProjectsDTO;
import io.testomat.api.dtos.SuiteDto;
import net.datafaker.Faker;
import org.testng.annotations.Test;

import java.util.List;

public class CreateTestsSuiteTests {

    static Faker faker = new Faker();


    @Test
    void testName() {
        var jwtToken = new AuthController().loginUser("newromka@gmail.com", "p8qfCZ7Jv7pT!hh").getJwt();

        var projectTitle = ((ProjectsDTO) new ProjectController()
                .withToken(jwtToken)
                .getProjects().toObject()).getData().get(0).getAttributes().getTitle();

        new ProjectController()
                .withToken(jwtToken)
                .getProjects();

        var suitesController = new SuitesController()
                .withToken(jwtToken);

        var suite = (SuiteDto) suitesController.createTestSuite(
                projectTitle,
                generateSuiteDto()
        ).toObject();

        suitesController.getSuiteForProjectById(projectTitle, suite.getData().getId());

        suitesController.getAllSuitesForProject(projectTitle);
    }


    private static List<SuiteDto> testNames() {
        return List.of(generateSuiteDto(), generateSuiteDto(), generateSuiteDto());
    }


    private static SuiteDto generateSuiteDto() {
        return SuiteDto.builder()
                .data(DataItem.builder()
                        .type("suites")
                        .attributes(Attributes.builder()
                                .title(faker.book().title())
                                .description(faker.book().publisher())
                                .build())
                        .build())
                .build();
    }

}
