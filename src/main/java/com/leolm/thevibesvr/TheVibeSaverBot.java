package com.leolm.thevibesvr;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TheVibeSaverBot {
	    static final String BASE_URL = "https://api.telegram.org/bot";
	    static final String BOT_ID = "INSERT HERE YOUR TELEGRAM BOT ID";
	    static final String GET_ALL_MESSAGES = "getUpdates";

	    public static void main(String[] args) {
	        try{
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(new URI(BASE_URL + BOT_ID + GET_ALL_MESSAGES))
	                .GET()
	                .build();
	        
	        HttpClient client = HttpClient.newHttpClient();
	        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
	        System.out.println(request.toString());
	        System.out.println(response.headers());
	        //System.out.println(response.body());
	        String json = response.body();
	        ObjectMapper objectMapper = new ObjectMapper();
	        JsonNode jsonNode = objectMapper.readTree(json);
	        JsonNode result = jsonNode.get("result");
	        if(result!=null) {
	        	System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result));
	     	   
	        }
	        }catch(URISyntaxException e){
	            System.out.println(e.getMessage()+"/n \t A URI está fora do padrão!!!!");
	        }catch(InterruptedException e){
	            System.out.println(e.getMessage()+"/n \t O programa e/ou a requisição foi interrompido inesperadamente!!!");
	        }
	        catch(JsonMappingException | JsonParseException e) {
	        	System.out.println(e.getMessage()+"\n \t houve um problema ao converter o json da resposta!!!");
	        }catch(IOException e){
	            System.out.println(e.getMessage()+"/n \t Erro de I/O");
	        }catch(Exception e ){
       		System.out.println(e.getMessage()+"/n \t Uma Exceção inesperada ocorreu!!! se ela persistir contate o Desenvolvimento");
	        }
	    }
	    }

