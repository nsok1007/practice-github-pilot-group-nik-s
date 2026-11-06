package com.launchcode.ai_tutorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(properties = {
		"spring.datasource.url=jdbc:h2:mem:class-manager-test",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop"
})
class AiTutorialApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void managesTeachersThroughRestApi() throws Exception {
		String location = mockMvc.perform(post("/teachers")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Ada Lovelace\",\"email\":\"ada@example.com\",\"subject\":\"Math\"}"))
				.andExpect(status().isCreated())
				.andReturn()
				.getResponse()
				.getHeader("Location");

		mockMvc.perform(get(location))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Ada Lovelace"));
		mockMvc.perform(get("/teachers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email").value("ada@example.com"));
		mockMvc.perform(put(location)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Ada Byron\",\"email\":\"ada@example.com\",\"subject\":\"Science\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Ada Byron"));
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}

	@Test
	void managesStudentsThroughRestApi() throws Exception {
		String location = mockMvc.perform(post("/students")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Grace Hopper\",\"email\":\"grace@example.com\",\"gradeLevel\":\"10\"}"))
				.andExpect(status().isCreated())
				.andReturn()
				.getResponse()
				.getHeader("Location");

		mockMvc.perform(get(location))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.gradeLevel").value("10"));
		mockMvc.perform(put(location)
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Grace Hopper\",\"email\":\"grace@example.com\",\"gradeLevel\":\"11\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.gradeLevel").value("11"));
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());
		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}
