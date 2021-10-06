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

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * Hendor Bot Pro
 *
 * Open-source
 * @author Rainer Regan <hi@rainerregan.com>
 */
@SpringBootApplication
@LineMessageHandler
public class HendorbotproApplication {

	/**
	 * Line Messaging Client Object
	 */
	@Autowired
	private LineMessagingClient lineMessagingClient;

	/**
	 * Main Function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(HendorbotproApplication.class, args);
	}

	/**
	 * Handle Text Event.
	 *
	 * Fungsi yang menangkap pesan masuk dalam object MessageEvent
	 * @param messageEvent
	 */
	@EventMapping
	public void handleTextEvent(MessageEvent<TextMessageContent> messageEvent){

		/**
		 * Pesan yang masuk dari pengguna
		 */
		Pesan pesan = new Pesan(messageEvent.getMessage().getText().toLowerCase());

		/**
		 * Pesan with split commands
		 *
		 * array(command, isi pesan)
		 */
		String[] pesanSplitWithCommands = pesan.getPesanSplitCommands(pesan.getPesan());

		System.out.println("Pesan: " + pesanSplitWithCommands[0] + "," + pesanSplitWithCommands[1]);

		/**
		 * Mendapatkan jawaban yang sesuai dengan command
		 */
		String jawabanReply = returnJawaban(pesanSplitWithCommands);

		/**
		 * Jika ada reply
		 */
		if(jawabanReply != null){
			/**
			 * Membalas pesan
			 */
			balasPesan(
					messageEvent.getReplyToken(),
					jawabanReply
				);
		}

	}

	/**
	 * Mereturn jawaban sesuai dengan command
	 * @param pesanSplitWithCommands
	 * @return
	 */
	private String returnJawaban(String[] pesanSplitWithCommands){
		String jawaban = null;

		/**
		 * Jika ada command
		 */
		if(pesanSplitWithCommands.length > 1){

			/**
			 * Jika commands valid
			 */
			if(Settings.BOT_COMMANDS_LIST.contains(pesanSplitWithCommands[0])){
//				System.out.println("Command: " + pesanSplitWithCommands[0]);

				switch (pesanSplitWithCommands[0]){
					/**
					 * APAKAH
					 */
					case "apakah":
						jawaban = BasicFunctions.getRandomJawaban();
						break;
					case "mau":
						if(pesanSplitWithCommands[1].endsWith("?")){
							jawaban = BasicFunctions.getRandomJawaban();
						}
						break;
					case "!pilih":
							jawaban = BasicFunctions.getPilihan(pesanSplitWithCommands[1]);
						break;
					case "!help":
					case "!commands":
						jawaban = BasicFunctions.getCommands();
						break;
				}
			}
		}

		return jawaban;
	}

	/**
	 * Balas chat user
	 *
	 * @param replyToken
	 * @param jawaban
	 */
	private void balasPesan(String replyToken, String jawaban){
		TextMessage jawabanDalamBentukTextMessage = new TextMessage(jawaban);
		try {
			lineMessagingClient
					.replyMessage(new ReplyMessage(replyToken, jawabanDalamBentukTextMessage))
					.get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println("Ada error saat ingin membalas chat: "+ e.getMessage());
		}
	}

}
