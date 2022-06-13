package com.defensoria.integral.controller.sigedepu;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.sigedepu2.Videos;
import com.defensoria.integral.repository.sigedepu2.VideosRepository;

@RestController
public class VideosRestController {
	@Autowired
	VideosRepository  videosRepository;
	
	@PostMapping (value="/listaVideos")
	public ResponseEntity<?> ListaVideos(@RequestBody Videos video) throws ParseException{
		
		List<Videos> listaVideos = videosRepository.findAll(Example.of(video));
		return new ResponseEntity <List<Videos>>(listaVideos, HttpStatus.OK);
	}

}
