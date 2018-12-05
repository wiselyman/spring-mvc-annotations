package top.wisely.springmvcannotations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/people")
@Slf4j
public class PersonRestController {

    @GetMapping("/{id}")
    public void get(@PathVariable Long id){
        log.info("--------" + id + "--------");
    }

    @GetMapping("/headerInfo")
    public void header(@RequestHeader("User-Agent") String userAgent){
        log.info("--------" + userAgent + "--------");
    }

    @GetMapping("/getCookieValue")
    public void getCookieValue(@CookieValue("tz") String timeZone){
        log.info("--------" + timeZone + "--------");

    }

    @GetMapping("/findByName")
    public void findByName(@RequestParam String name){
        log.info("--------" + name + "--------");

    }

    @GetMapping("/showHttpStatus")
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    public void showHttpStatus(){
        log.info("-------- showHttpStatus" + "--------");

    }
    @GetMapping("/{id}/cross")
    @CrossOrigin({"*"})
    public void crossGet(@PathVariable Long id){
        log.info("--------" + id + "--------");
    }

    @PostMapping
    public void save(@RequestBody Person person){
        log.info("--------" + person + "--------");
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id , @RequestBody Person person){

        log.info("--------" + id + "--------");
        log.info("--------" + person + "--------");

    }

    @PatchMapping
    public void patch(@PathVariable Long id , @RequestBody Person person){
        log.info("--------" + id + "--------");
        log.info("--------" + person + "--------");

    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id){
        log.info("--------" + id + "--------");
    }



    @PostMapping("/upload")
    public void upload(@RequestPart("file") MultipartFile image){
        log.info("--------" + image.getOriginalFilename() + "--------");
    }

    @GetMapping("/exception")
    public void exception() throws Exception {
        throw new Exception();
    }

    @GetMapping("/ioException")
    public void ioException() throws IOException {
        throw new IOException();
    }

    @GetMapping("/binder/{dog-txt}")
    public void binder(@PathVariable("dog-txt") Dog dog) {
        log.info("--------" + dog + "--------");
    }




}
