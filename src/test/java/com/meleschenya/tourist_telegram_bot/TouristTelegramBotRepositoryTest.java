//package com.meleschenya.tourist_telegram_bot;
//
//import com.meleschenya.tourist_telegram_bot.entity.City;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@SpringBootTest
//public class TouristTelegramBotRepositoryTest extends AbstractTest{
//	@Override
//	@Before
//	public void setUp() {
//		super.setUp();
//	}
//
//	@Test
//	public void getCityList() throws Exception {
//		String uri = "/getAllCities";
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		City[] cityList = super.mapFromJson(content, City[].class);
//		assertTrue(cityList.length > 0);
//	}
//
//	@Test
//	public void createCIty() throws Exception {
//		String uri = "/addCity";
//		City city = City.builder()
//				.name("testName")
//				.description("testDesc")
//				.country("testCountry")
//				.build();
//		String inputJson = super.mapToJson(city);
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.content(inputJson)).andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		assertEquals(content, "City is created successfully");
//	}
//
//	@Test
//	public void updateCityBiId() throws Exception {
//		String uri = "/updateCityById/14";
//		City city = City.builder()
//				.id(1212)
//				.name("testNameUPD_ID")
//				.description("testDescUPD_ID")
//				.country("testCountryUPD_ID")
//				.build();
//		String inputJson = super.mapToJson(city);
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.content(inputJson)).andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		assertEquals(content, "City is updated successfully");
//	}
//
//	@Test
//	public void updateCity() throws Exception {
//		String uri = "/updateCity";
//		City city = City.builder()
//				.id(1212)
//				.name("testNameUPD")
//				.description("testDescUPD")
//				.country("testCountryUPD")
//				.build();
//		String inputJson = super.mapToJson(city);
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//				.contentType(MediaType.APPLICATION_JSON_VALUE)
//				.content(inputJson)).andReturn();
//
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		assertEquals(content, "City is updated successfully");
//	}
//
//	@Test
//	public void deleteCIty() throws Exception {
//		String uri = "/delete/14";
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
//		int status = mvcResult.getResponse().getStatus();
//		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		assertEquals(content, "City is deleted successfully");
//	}
//}