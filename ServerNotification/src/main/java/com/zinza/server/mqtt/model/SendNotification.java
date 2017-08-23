package com.zinza.server.mqtt.model;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class SendNotification {
	private MqttConnectOptions connectOptions;
	private MqttClient client;
	private String broker;
	private String clientId;
	private MemoryPersistence persistence;
	// function constructor
	public SendNotification(String broker, String clientId) {
		super();
		this.broker = broker;
		this.clientId = clientId;
		this.persistence=new MemoryPersistence();
	}

	// connect with authentic
	public boolean connect(String userName, String password) {
		this.connectOptions = new MqttConnectOptions();
		this.connectOptions.setUserName(userName);
		this.connectOptions.setPassword(password.toCharArray());
		try {
			this.client=new MqttClient(broker, clientId,persistence);
			this.client.connect(this.connectOptions);
			return true;

		} catch (MqttSecurityException e) {
			e.printStackTrace();
			return false;

		} catch (MqttException e) {
			e.printStackTrace();
			return false;

		}
	}

	// connect without authentic
	public boolean connect() {
		this.connectOptions = new MqttConnectOptions();
		try {
			this.client=new MqttClient(broker, clientId);
			this.client.connect(this.connectOptions);
			return true;

		} catch (MqttSecurityException e) {
			e.printStackTrace();
			return false;

		} catch (MqttException e) {
			e.printStackTrace();
			return false;
		}
	}
	//publish message to a topic
	public boolean sendNotification(String topic,String message) {
		MqttMessage mqttMessage = new MqttMessage(message.getBytes());
		try {
			this.client.publish(topic, mqttMessage);
			return true;
			
		} catch (MqttPersistenceException e) {
			e.printStackTrace();
			return false;
			
		} catch (MqttException e) {
			e.printStackTrace();
			return false;
			
		}finally {
			disconnect();
		}
	}
	public void disconnect() {
		try {
			this.client.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}		
	}
}
