package rtls;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONObject;
import com.google.gson.*;

public class MQTTClient implements MqttCallback {

	private MqttClient myClient;
	private MqttConnectOptions connOpt;

	static final String BROKER_URL = "tcp://34.230.40.176:1883";

	/**
	 * 
	 * connectionLost This callback is invoked upon losing the MQTT connection.
	 * 
	 */
	@Override
	public void connectionLost(Throwable t) {
		System.out.println("Connection lost!");
		// code to reconnect to the broker would go here if desired
	}

	/**
	 * 
	 * deliveryComplete This callback is invoked when a message published by
	 * this client is successfully received by the broker.
	 * @throws MqttException 
	 * 
	 */
	public void deliveryComplete(MqttDeliveryToken token) throws MqttException {
		 System.out.println("Pub complete" + new
		 String(token.getMessage().getPayload()));
	}

	/**
	 * 
	 * MAIN
	 * 
	 */
	public static void main(String[] args) {
		MQTTClient smc = new MQTTClient();
		smc.runClient();
	}

	/**
	 * 
	 * runClient The main functionality of this simple example. Create a MQTT
	 * client, connect to broker, pub/sub, disconnect.
	 * 
	 */
	public void runClient() {
		// setup MQTT Client
		String clientID = "MySimpleMQTTClient";
		connOpt = new MqttConnectOptions();

		connOpt.setCleanSession(true);
		connOpt.setKeepAliveInterval(30);
		

		// Connect to Broker
		try {
			myClient = new MqttClient(BROKER_URL, clientID);
			myClient.setCallback(this);
			myClient.connect(connOpt);
			
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		System.out.println("Connected to " + BROKER_URL);

		// setup topic filter
		String myTopic = "Lights";
		String myTopic2 = "SendPlayerData";
		

		// subscribe to topic if subscriber
		try {
			int subQoS = 0;
			myClient.subscribe(myTopic, subQoS);
			int subQoS1 = 1;
			myClient.subscribe(myTopic2, subQoS1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// disconnect
		try

		{
			// wait to ensure subscribed messages are delivered
			Thread.sleep(1000000000);
			myClient.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		String Meldung = new String(message.toString());
		String AntwortA = new String("Answer1");
		String AntwortB = new String("Answer2");
		String AntwortC = new String("Answer3");
		System.out.println(Meldung);
		if(Meldung.contentEquals(AntwortA)) {

			String content = rtls.getposition.Feld14(null);
			String topicA = "GetWinner";
			MqttMessage Antwort = new MqttMessage(content.getBytes());
			
			myClient.publish(topicA, Antwort);	
			
			
		}
		else if(Meldung.contentEquals(AntwortB)) {
			String content = rtls.getposition.Feld15(null);
			String topicA = "GetWinner";
			MqttMessage Antwort = new MqttMessage(content.getBytes());
			
			myClient.publish(topicA, Antwort);	
			
			
		}	
		else if(Meldung.contentEquals(AntwortC)) {
			String content = rtls.getposition.Feld16(null);
			String topicA = "GetWinner";
			MqttMessage Antwort = new MqttMessage(content.getBytes());
			
			myClient.publish(topicA, Antwort);	
			
			
		}
		else if(Meldung.contains("username")) {
			
			Gson g = new Gson();
			JSONObject playerstats = new JSONObject(g.toJson(Meldung));
			String username = playerstats.getString("username");
			String age = playerstats.getString("userage");
			JSONObject payloadJSON2 = new JSONObject()
								.put("id",4)
								.put("type","TEXT")
								.put("data",username);
			
			JSONObject payloadJSON3 = new JSONObject()
					.put("id",3)
					.put("type","TEXT")
					.put("data",age);
			String payload = "[" + payloadJSON3.toString() + "," + payloadJSON2.toString() + "]";
			String content = rtls.SetUser.Test(payload);
			
			System.out.println(content);
			
			
			JSONObject payloadReturnJSON = new JSONObject()
					.put("badgeId", content)
					.put("name", username)
					.put("age", age);
			String payloadReturn = payloadReturnJSON.toString();
			String topicB = "SetUser";
			MqttMessage Antwort = new MqttMessage((payloadReturn).getBytes());
			
			myClient.publish(topicB, Antwort);
		}
	}
}