package com.company.resourceapi;

import com.company.resourceapi.repositories.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ResourceapiApplicationTests {

	public static final String ENDPOINT = "/api/v2/projects";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ProjectRepository projectRepository;

	@Test
	public void WhenGetProjectExists_ThenOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get(ENDPOINT + "/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("Sample Project"));
	}

	@Test
	public void WhenGetProjectDoesNotExists_ThenNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get(ENDPOINT + "/520")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void WhenPostProjectValid_ThenCreated() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":null,\"externalId\":\"INTEGRATIONTEST\",\"name\":\"Integration Test\",\"sdlcSystem\":{\"id\":1 }}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void WhenPostProjectInValid_ThenNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":null,\"externalId\":\"INTEGRATIONTEST\",\"name\":\"Integration Test\",\"sdlcSystem\":{\"id\":585 }}"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void WhenPostProjectInValidField_ThenBadRequest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":null,\"externalId\":\"null\",\"name\":\"Integration Test\",\"sdlcSystem\":\"null\"}"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void WhenPostProjectViolateConstraint_ThenConflict() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.post(ENDPOINT)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":null,\"externalId\":\"SAMPLEPROJECT\",\"name\":\"Invalid Constraint\",\"sdlcSystem\":{\"id\":1 }}"))
				.andExpect(status().isConflict());
	}

	@Test
	public void WhenPatchProjectValid_ThenOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.patch(ENDPOINT + "/5")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":5,\"externalId\":\"SAMPLEPROJECTNINE\",\"name\":\"Valid Patch\"}"))
				.andExpect(status().isOk());
	}

	@Test
	public void WhenPatchProjectInvalidId_ThenNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.patch(ENDPOINT + "/550")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":55,\"externalId\":\"SAMPLEPROJECTNINE\"}"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void WhenPatchProjectInvalidSdlcSystem_ThenNotFound() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.patch(ENDPOINT + "/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":2,\"externalId\":\"SAMPLEPROJECTTen\",\"sdlcSystem\":{\"id\":550 }}"))
				.andExpect(status().isNotFound());
	}

	@Test
	public void WhenPatchProjectViolateConstraint_ThenConflict() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.patch(ENDPOINT + "/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":2,\"externalId\":\"SAMPLEPROJECT\",\"name\":\"Invalid Constraint\",\"sdlcSystem\":{\"id\":1 }}"))
				.andExpect(status().isConflict());
	}
}
