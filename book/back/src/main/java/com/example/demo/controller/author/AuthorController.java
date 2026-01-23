package com.example.demo.controller.author;

import com.example.demo.dto.author.AuthorCreateRequest;
import com.example.demo.dto.author.AuthorResponse;
import com.example.demo.dto.author.AuthorUpdateRequest;
import com.example.demo.service.author.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @PostMapping
//    public AuthorResponse create(@Valid @RequestBody AuthorCreateRequest req) {
//        return service.create(req);
    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorCreateRequest req) {
        AuthorResponse res = service.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
        // == TRẢ VỀ RESPONSE ENTITY VỚI TRẠNG THÁI 201 CREATED ==
//    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorCreateRequest req) {return ResponseEntity.ok(service.create(req));}

    }

    @GetMapping("/{id}")
    public AuthorResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<AuthorResponse> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public AuthorResponse update(@PathVariable Long id,
                                 @Valid @RequestBody AuthorUpdateRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}


    //    @PostMapping
//    public AuthorResponse create(@Valid @RequestBody AuthorCreateRequest req) {
//        return service.create(req);
//    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorCreateRequest req) {return ResponseEntity.ok(service.create(req));}
//    @PostMapping
//    public ResponseEntity<AuthorResponse> create(@Valid @RequestBody AuthorCreateRequest req) {
//        AuthorResponse res = service.create(req);
//        return ResponseEntity.status(HttpStatus.CREATED).body(res);
//    }
// == XỬ LÝ NGOẠI LỆ TOÀN DIỆN TRONG CONTROLLER ==
//@PostMapping
//public ResponseEntity<?> create(@Valid @RequestBody AuthorCreateRequest req) {
//    try {
//        AuthorResponse res = service.create(req);
//        return ResponseEntity.status(HttpStatus.CREATED).body(res);
//    } catch (IllegalArgumentException e) {
//        return ResponseEntity.badRequest().body(e.getMessage());
//    } catch (EntityNotFoundException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error");
//    }
//}
