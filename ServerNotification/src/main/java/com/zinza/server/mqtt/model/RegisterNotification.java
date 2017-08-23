package com.zinza.server.mqtt.model;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class RegisterNotification {
	private MqttConnectOptions connectOptions;
	private MqttClient client;
	private String broker;
	private String clientId;

	// function Constructor
	public RegisterNotification(String broker, String clientId) {
		super();
		this.broker = broker;
		this.clientId = clientId;
	}

	// connect with authentic
	public boolean connect(String userName, String password) {
		this.connectOptions = new MqttConnectOptions();
		this.connectOptions.setUserName(userName);
		this.connectOptions.setPassword(password.toCharArray());
		try {
			this.client = new MqttClient(broker, clientId);
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
			this.client = new MqttClient(broker, clientId);
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

	// subscribe a topic
	public boolean follow(String topic) {
		try {
			this.client.subscribe(topic);
			return true;
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	// receive message
	public void receiveMessage(MqttCallback callback) {
		this.client.setCallback(new MqttCallback() {

			@Override
			public void messageArrived(String arg0, MqttMessage arg1) throws Exception {

				callback.messageArrived(arg0, arg1);
			}

			@Override
			public void deliveryComplete(IMqttDeliveryToken arg0) {

				callback.deliveryComplete(arg0);
			}

			@Override
			public void connectionLost(Throwable arg0) {

				callback.connectionLost(arg0);
			}
		});
	}
}
