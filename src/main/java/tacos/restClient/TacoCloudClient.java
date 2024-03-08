package tacos.restClient;

import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;
import tacos.Ingredient;

public class TacoCloudClient {
    RestTemplate rest;
    Traverson traverson;

    public TacoCloudClient(RestTemplate rest, Traverson traverson) {
        this.rest = rest;
        this.traverson = traverson;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, ingredientId);
    }
    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}",
                ingredient, ingredient.getId());
    }
    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}",
                ingredient.getId());
    }
    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }

  /*  public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class, urlVariables);
    }
    public Ingredient getIngredientById(String ingredientId) {
Map<String, String> urlVariables = new HashMap<>();
urlVariables.put("id", ingredientId);
URI url = UriComponentsBuilder
.fromHttpUrl("http://localhost:8080/ingredients/{id}")
.build(urlVariables);
return rest.getForObject(url, Ingredient.class);
}
public Ingredient getIngredientById(String ingredientId) {
ResponseEntity<Ingredient> responseEntity =
rest.getForEntity("http://localhost:8080/ingredients/{id}",
Ingredient.class, ingredientId);
log.info("Fetched time: {}",
responseEntity.getHeaders().getDate());
return responseEntity.getBody();
}
public java.net.URI createIngredient(Ingredient ingredient) {  //местоположение созданнго
return rest.postForLocation("http://localhost:8080/ingredients",
ingredient);
}
public Ingredient createIngredient(Ingredient ingredient) { //ресурс и местоположение
ResponseEntity<Ingredient> responseEntity =
rest.postForEntity("http://localhost:8080/ingredients",
ingredient,
Ingredient.class);
log.info("New resource created at {}",
responseEntity.getHeaders().getLocation());
return responseEntity.getBody();
}
   */
}
