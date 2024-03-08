package tacos.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.TacoOrder;

import tacos.Users;
import tacos.data.OrderRepository;


import javax.validation.Valid;
import java.awt.print.Pageable;

@Controller

@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

  //private OrderProps orderProps;
  private OrderRepository orderRepo;

  public OrderController(OrderRepository orderRepo) {
    this.orderRepo = orderRepo;
   // this.orderProps = orderProps;
  }

  @GetMapping("/current")
  public String orderForm(@AuthenticationPrincipal Users user,
      @ModelAttribute TacoOrder order) {
    if (order.getDeliveryName() == null) {
      order.setDeliveryName(user.getFullname());
    }
    if (order.getDeliveryStreet() == null) {
      order.setDeliveryStreet(user.getStreet());
    }
    if (order.getDeliveryCity() == null) {
      order.setDeliveryCity(user.getCity());
    }
    if (order.getDeliveryState() == null) {
      order.setDeliveryState(user.getState());
    }
    if (order.getDeliveryZip() == null) {
      order.setDeliveryZip(user.getZip());
    }

    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors,
      SessionStatus sessionStatus, Users user) {

    if (errors.hasErrors()) {
      return "orderForm";
    }

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    user = (Users) authentication.getPrincipal();
    order.setUser(user);
    orderRepo.save(order);
    sessionStatus.setComplete();

    return "redirect:/";
  }
  /*@PutMapping(path="/{orderId}", consumes="application/json")
  public TacoOrder putOrder(
          @PathVariable("orderId") Long orderId,
          @RequestBody TacoOrder order) {
    order.setId(orderId);
    return orderRepo.save(order);
  }
  @PatchMapping(path="/{orderId}", consumes="application/json")
  public TacoOrder patchOrder(@PathVariable("orderId") Long orderId,
                              @RequestBody TacoOrder patch) {
    TacoOrder order = orderRepo.findById(orderId).get();
    if (patch.getDeliveryName() != null) {
      order.setDeliveryName(patch.getDeliveryName());
    }
    if (patch.getDeliveryStreet() != null) {
      order.setDeliveryStreet(patch.getDeliveryStreet());
    }
    if (patch.getDeliveryCity() != null) {
      order.setDeliveryCity(patch.getDeliveryCity());
    }
    if (patch.getDeliveryState() != null) {
      order.setDeliveryState(patch.getDeliveryState());
    }
    if (patch.getDeliveryZip() != null) {

      order.setDeliveryZip(patch.getDeliveryZip());
    }
    if (patch.getCcNumber() != null) {
      order.setCcNumber(patch.getCcNumber());
    }
    if (patch.getCcExpiration() != null) {
      order.setCcExpiration(patch.getCcExpiration());
    }
    if (patch.getCcCVV() != null) {
      order.setCcCVV(patch.getCcCVV());
    }
    return orderRepo.save(order);
  }

  @DeleteMapping("/{orderId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable("orderId") Long orderId) {
    try {
      orderRepo.deleteById(orderId);
    } catch (EmptyResultDataAccessException e) {}
  }

   */
 /* @GetMapping
  public String ordersForUser(
          @AuthenticationPrincipal Users user, Model model) {
    Pageable pageable = (Pageable) PageRequest.of(0,orderProps.getPageSize());
    model.addAttribute("orders",
            orderRepo.findByUserOrderByPlacedAtDesc(pageable, user));
    return "orderList";
  }

  */
}
