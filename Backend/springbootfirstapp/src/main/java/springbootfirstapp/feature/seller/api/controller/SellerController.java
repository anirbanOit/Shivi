package springbootfirstapp.feature.seller.api.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springbootfirstapp.domain.entity.Seller;
import springbootfirstapp.domain.repo.SellerRepo;

@RestController
@RequestMapping("/seller")
public class SellerController {
	@Autowired
	SellerRepo rp;

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	@ResponseBody
	public List<Seller> findall() {
		return rp.findAll();

	}
}