package com.springBoot.Websocket.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Message {

	private String  sender;
	private String receiver;
	private String message;
	private List<Integer> arr;
}
