package com.netty.chat.client.client;

import java.util.concurrent.ConcurrentHashMap;

public class OlineClients {
	
	private ConcurrentHashMap<Integer, Client> clientMap = new ConcurrentHashMap<Integer, Client>();
	
	public static OlineClients instance = new OlineClients();
	public static OlineClients getInstance(){return instance;}
	private OlineClients(){}
	
	public void addClient(Client client){
		int clientId = client.getId();
		if(clientMap.putIfAbsent(clientId,client) != null){
			System.err.println("put client error,there is a client refect this id");
		}
	}
	
	public Client getLatestClient(){
		int latestId = Client.getNowId() - 1;
		Client client = clientMap.get(latestId);
		if(client != null){
			return client;
		}
		return null;
	}
	
	public void removeClient(int id){
		clientMap.remove(id);
	}
	
	public void removeClient(Client client){
		clientMap.remove(client.getId());
	}
}
