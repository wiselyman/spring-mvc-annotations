
# Spring MVC注解大全

目前的主流应用都是前后端分离，我们主要关注于基于REST的控制器。当前例子基于`Spring Boot 2.1.1.RELEASE`。

### 1. `@Controller`

将当前类注册为`Spring MVC`的控制器。

### 2. `@RestController` 

将当前类注册为`Spring MVC`的REST控制器，它组合类一个`@ResponseBody`。

``` java
@RestController
public class PersonRestController {
    
}
```

### 3. `@RequestMapping`

通用的路径到MVC方法的映射，可注解在类上或方法上，支持`GET` `POST` `PUT` `DELETE` `PATCH`，我们平常应该使用专用的注解，`@RequestMapping`放置在类一级。

``` java
@RestController
@RequestMapping("/people")
public class PersonRestController {
    
}

```

### 4. `@PathVariable`

可以在请求路径中获取变量。

``` java
   
@GetMapping("/{id}")
public void get(@PathVariable Long id){
    log.info("--------" + id + "--------");
}

```

访问路径为：`http://localhost/people/1`


### 5. `@RequestParam`

获取请求参数。

``` java
@GetMapping("/findByName")
public void findByName(@RequestParam String name){
    log.info("--------" + name + "--------");

}

```

访问路径为：`http://localhost/people/findByName?name=wyf`

### 6. `@RequestHeader`

获取`请求头`的信息。

``` java
@GetMapping("/headerInfo")
public void header(@RequestHeader("User-Agent") String userAgent){
    log.info("--------" + userAgent + "--------");
}

```

### 7. `@CookieValue`

获取客户端的cookie中的信息。

``` java
@GetMapping("/getCookieValue")
public void getCookieValue(@CookieValue("tz") String timeZone){
    log.info("--------" + timeZone + "--------");

}

```

### 8. `@RequestBody`

从`请求体`中获取数据。

``` java
@PostMapping
public void save(@RequestBody Person person){
    log.info("--------" + person + "--------");
}


```

### 9. `@ResponseBody`

将返回数据写入`返回体`中,`@RestController`已包含。

### 10. `@GetMapping`

映射`Http`的`GET`方法，获取信息。

``` java
@GetMapping("/{id}")
public void get(@PathVariable Long id){}

```

### 11. `@PostMapping`

映射`Http`的`POST`方法，保存信息。

``` java
PostMapping
public void save(@RequestBody Person person){}

```

### 12. `@PutMapping`

映射`Http`的`PUT`方法，更新信息。

``` java
@PutMapping("/{id}")
public void update(@PathVariable Long id , @RequestBody Person person){}

```

### 13. `@DeleteMapping`

映射`Http`的`DELETE`方法，删除信息。


``` java
@DeleteMapping("/{id}")
public void remove(@PathVariable Long id){}

```

### 14. `@PatchMapping`

映射`Http`的`PATCH`方法，更新部分信息。

``` java
@PatchMapping
public void patch(@PathVariable Long id , @RequestBody Person person){}

```

### 15.`@ControllerAdvice`

用来处理跨控制器的统一处理，如：异常处理(`ExceptionHandler`)，数据转换(`@InitBinder`)等，它组合了一个`@Component`注解，所以不用再手工注册bean。

``` java
@ControllerAdvice
public class WiselyControllerAdvice {}

```

### 16. `@ExceptionHandler`

针对控制器的全局异常处理。

``` java
@ControllerAdvice
public class WiselyControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        return "服务器异常";
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> IOExceptionnHandler(HttpServletRequest request, Exception exception) {
       return new ResponseEntity<String>("服务器IO异常",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

```

控制器中的演示：

``` java
@GetMapping("/exception")
public void exception() throws Exception {
    throw new Exception();
}

@GetMapping("/ioException")
public void ioException() throws IOException {
    throw new IOException();
}

```

### 17.`@InitBinder`

将制定格式的字符串专程对象，如将`2-wyf`(id-name),转换成dog对象。

``` java
@ControllerAdvice
public class WiselyControllerAdvice {

    @InitBinder
    public void dataBinding(WebDataBinder binder) {

        binder.registerCustomEditor(Dog.class, new DogPropertiesEditor());
    }

}

```

自定义的属性编辑器:

``` java
public class DogPropertiesEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] array = text.split("-");
        Long id = new Long(array[0]);
        String name = array[1];
        setValue(new Dog(id , name));
    }

}
```

演示控制器：

``` java
@GetMapping("/binder/{dog-txt}")
public void binder(@PathVariable("dog-txt") Dog dog) {
    log.info("--------" + dog + "--------");
}

```

访问地址：`http://localhost/binder/2-wyf`

### 18. `@ResponseStatus`

定制Http的Reponse的状态：

``` java
@GetMapping("/showHttpStatus")
@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public void showHttpStatus(){}

```

### 19. `@CrossOrigin`

在不同的域下的网页用脚本调用接口的时候存在这跨域的问题，可以用当前注解来允许可以跨域的域。

``` java
@GetMapping("/{id}/cross")
@CrossOrigin({"*"})
public void crossGet(@PathVariable Long id){}

```
### 20. `@RequestPart`

从参数重获取上传的文件：

``` java
@PostMapping("/upload")
public void upload(@RequestPart("file") MultipartFile image){
    log.info("--------" + image.getOriginalFilename() + "--------");
}

```

### 21. 源码地址

[https://github.com/wiselyman/spring-mvc-annotations.git](https://github.com/wiselyman/spring-mvc-annotations.git)