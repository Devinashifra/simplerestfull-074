package wsb.restful.wsb.restful;

import model.Product;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author HP
 */

@RestController
//class controller
public class controller {
    //Get API
    private static Map<String, Product> productRepo = new HashMap<>();
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        Product put = productRepo.put(honey.getId(), honey);
        
        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    
    //Delete API
    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String Id) {
        
        //tidak bisa menghapus 
        if(!productRepo.containsKey(Id)){
            return new ResponseEntity<>("Product delete failed", HttpStatus.NOT_FOUND);
        }
        
        //berhasil menghapus
        productRepo.remove(Id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
    
    //Update API (PUT API)
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("Id") String id, @RequestBody Product product) {
        
        //tidak bisa update (put)
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("Product update failed", HttpStatus.NOT_FOUND);
        }
        
        //berhasil update (put)
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }
    
    //Create API (Post API)
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct(){
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
}
