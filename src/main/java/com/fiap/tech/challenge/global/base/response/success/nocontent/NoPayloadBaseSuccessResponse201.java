package com.fiap.tech.challenge.global.base.response.success.nocontent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap.tech.challenge.global.base.dto.BaseResponseDTO;
import com.fiap.tech.challenge.global.base.response.success.BaseSuccessResponse201;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class NoPayloadBaseSuccessResponse201<T extends BaseResponseDTO> extends BaseSuccessResponse201<T> {

	public NoPayloadBaseSuccessResponse201() {
		super();
	}

	@Schema
	@Hidden
	@JsonIgnore
	public T getItem() {
		return this.item;
	}

	@Override
	public ResponseEntity<NoPayloadBaseSuccessResponse201<T>> buildResponseWithoutPayload() {
		return new ResponseEntity<>(HttpStatus.valueOf(this.status));
	}
}