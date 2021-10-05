package com.akmp.hendorbotpro;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@LineMessageHandler
public class HendorbotproApplication {

	@Autowired
	private LineMessagingClient lineMessagingClient;

	public static void main(String[] args) {
		SpringApplication.run(HendorbotproApplication.class, args);
	}

	@EventMapping
	public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent){
		String pesan = messageEvent.getMessage().getText().toLowerCase();
		String[] pesanSplit = pesan.split(" ");
		if(pesanSplit[0].equals("apakah")){
			String jawaban = getRandomJawaban();
			String replyToken = messageEvent.getReplyToken();
			balasChatDenganRandomJawaban(replyToken, jawaban);
		}
	}

	private String getRandomJawaban(){
		String jawaban = "";
		int random = new Random().nextInt();
		if(random%2==0){
			jawaban = "Ya";
		} else{
			jawaban = "Nggak";
		}
		return jawaban;
	}

	private void balasChatDenganRandomJawaban(String replyToken, String jawaban){
		TextMessage jawabanDalamBentukTextMessage = new TextMessage(jawaban);
		try {
			lineMessagingClient
					.replyMessage(new ReplyMessage(replyToken, jawabanDalamBentukTextMessage))
					.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Ada error saat ingin membalas chat");
		}
	}

}
