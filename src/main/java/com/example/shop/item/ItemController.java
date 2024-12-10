package com.example.shop.item;

import com.example.shop.comment.Comment;
import com.example.shop.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// Controller는 보통 데이터나 html 보내는 역활임
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    private final ItemService itemService;

    private final CommentRepository commentRepository;

//    private final S3Service s3Service;

    //    Lombok 없이 등록하려면
//    @Autowired
//    public ItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    // 템플릿엔진 쓰면 서버/Database의 데이터를 HTML에 집어넣을 수 있음.
    // 이걸 API 라고 하는것 같음
    @GetMapping("/list")
    public String list(Model model, Integer pageIdx, String searchText) {
        if(pageIdx == null){
            pageIdx = 1;
        }
        if(searchText == null){
            searchText = "";
        }
        Page<Item> result = itemRepository.findAllByTitleContains(searchText, PageRequest.of(pageIdx-1, 3));
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("items", result);
//        List<Item> items = itemRepository.findAll();
//        model.addAttribute("items", items);
////        model.addAttribute("전달할데이터이름", 데이터)
//        model.addAttribute("name", "비싼 바지");
        return "list";
    }

    @GetMapping(value = "/write")
    public String write() {
        return "write";
    }

    @PostMapping(value = "/add")
    // @RequestParam Map formData => input 100개면 100개를 Map 자료형으로 담아줌
    // @ModelAttribute Item item => <input> 데이터들을 바로 object로 변환하려면
    public String addPost(Authentication auth, Item item, MultipartFile itemFile) throws Exception {
        // 하나의 함수엔 하나의 기능만 담는게 좋다했다
        // 이 addPost 함수에선 다른 페이지로 redirect 시키는 기능과 DB 입출력 기능 두가지 존재
//        Item item = new Item();
//        item.setTitle(title);
//        item.setPrice(price);
//        itemRepository.save(item);
//        new ItemService().SaveItem(title, price);
//        다른 class의 함수를 사용할 때 new Class().함수()는 비추천임
//        이유는 /add를 통해 상품 100만개를 넣을 경우 100만개의 object 생성됨
//        다른데서 미리 new Class() 해놓고 재사용하는게 좋음(스프링한테 시키면 해줌)
        itemService.saveItem(auth.getName(), item, itemFile);
//        var test = new HashMap<>();
//        test.put("name", "kim");
//        test.put("age", 20);
//        System.out.println(test);
        // 특정페이지로 돌아가게 만들 수 있음(redirect)
        return "redirect:/list";
    }

    // URL 파라미터 문법쓰면 비슷한 URL의 API 여러개 만들 필요 없음
    @GetMapping(value = "/detail/{itemId}")
    public String detail(Model model, @PathVariable Long itemId) {
        // 댓글
        List<Comment> comments = commentRepository.findAllByParentId(itemId);
        model.addAttribute("comments", comments);

        // Q 왜 Optional 써야함?
        // JPA findById() 만든 사람이 쓰라는데요
        Optional<Item> result = itemRepository.findById(itemId);
        // result 안에 뭐가 들어가 있으면 if문 통과!
        if (result.isPresent()) {
            model.addAttribute("result", result.get());
            return "detail";
        } else {
            return "redirect:/list";
        }

    }
    
    // 모든 API의 에러를 캐치하려면
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return ResponseEntity.status(400).body("니잘못임");
    }
    
    @GetMapping(value = "/edit/{itemId}")
    public String edit(Model model, @PathVariable Long itemId){
        itemService.editService(model, itemId);
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String editItem(Item item){
        itemService.editItemService(item);
        return "redirect:/list";
    }

    @PostMapping("/test1")
    public String test(@RequestBody Map<String, Object> body) {
        System.out.println(body.get("name"));
        return "redirect:/list";
    }

    @GetMapping("/test2")
    String hashing(){
        var result = new BCryptPasswordEncoder().encode("해싱 할 문자");
        System.out.println(result);
        return "redirect:/list";
    }

//    @DeleteMapping("/item")
//    // query string으로 보낸 데이터 출력은 ? @RequestParam
//    public ResponseEntity<String> deleteItem(@RequestParam Long itemId){
//        itemRepository.deleteById(itemId);
//        return ResponseEntity.status(200).body("삭제완료");
//    }

    @GetMapping("/delete")
    @ResponseBody
    void deleteItem(@RequestParam Long itemId){
        itemRepository.deleteById(itemId);
    }

//    @PostMapping("/list/page/{pageNumber}")
//    public String getListPage(Model model, @PathVariable Integer pageNumber) {
//        Page<Item> result = itemRepository.findPageBy(PageRequest.of(pageNumber-1, 5));
////        result.getTotalPages(); -> 총 몇개의 페이지가 나오냐?
//        model.addAttribute("totalPages", result.getTotalPages());
//        model.addAttribute("items", result);
////        model.addAttribute("전달할데이터이름", 데이터)
//        model.addAttribute("name", "비싼 바지");
//        return "list";
//    }

    @GetMapping("presigned-url")
    String getURL(@RequestParam String filename){

        return "list";
    }

//    @PostMapping("/search")
//    String itemSearch(Model model, @RequestParam String searchText){
//        // 특정단어 포함된 모든 행을 가져오려면?
//        List<Item> searchItems = itemRepository.findAllByTitleContains(searchText);
//        model.addAttribute("items", searchItems);
//        return "redirect:/list";
//    }

    // HTML에 서버데이터 넣어서 보내주려면
    // 1. Model model 추가
    // 2. model.addAttribute(작명, 데이터)
    // 3. th:text="${작명}"

    // JPA로 데이터 입출력하려면 3-step
    // 1. repository 만들기
    // 2. 원하는 클래스에 repository 등록
    // 3. repository.입출력문법() 쓰기
    
    // 함수 하나에는 한가지 기능만 담는걸 권장
    // 하나의 클래스엔 비슷한 기능의 함수들만 보관하는게 좋음

    // 의존성 주입(Dependency Injection)
    // 1. new Class() 할 클래스에서 @Service
    // 2. 사용할 곳에서 변수로 등록하기
    // 3. 변수사용

    // Dependency Injection 왜 하는 것임?
    // 1. object 여러개 안뽑아도 되어서 효율적임
    // 2. 클래스간의 커플링을 줄일 수 있음

    // 서버로 요청날리는 법
    // 1. 주소창에 URL 적기(GET)
    // 2. <form> 전송 (GET, POST)
    // 3. AJAX(GET, POST, PUT, DELETE 등) 
    // AJAX : 새로고침없이 서버로 요청날리는 자바스크립트 코드
    // AJAX 장점 : 새로고침 없이 요청가능

    // 새로 배운거
    // 1. 새로고침 없이 요청날리고 데이터 받아오려면 AJAX
    // 2. query string, URL parameter 써도 서버로 데이터 전송가능
    // 3. 자바스크립트 안에 Thymeleaf 변수 넣기 가능
}
