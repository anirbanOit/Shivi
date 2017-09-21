package springbootfirstapp.feature.customer.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springbootfirstapp.domain.entity.Customer;
import springbootfirstapp.domain.entity.CustomerPatch;
import springbootfirstapp.domain.repo.CustomerRepo;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerRepo rp;

	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> findall() {
		return rp.findAll();

	}

	@RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Customer search(@PathVariable("id") final int id) {
		return rp.findOne(Integer.valueOf(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	// @Patch(repository = CustomerRepo.class, id = String.class)
	public Customer patch(@PathVariable Integer id, @RequestBody CustomerPatch customer) {
		Customer customer1 = rp.findOne(id);
		customer1.setName(customer.getName());

		return rp.save(customer1);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Customer add(@RequestBody Customer customer) {
		Customer customer1 = new Customer();
		customer1.setName(customer.getName());
		customer1.setPhone(customer.getPhone());
		return rp.saveAndFlush(customer1);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Customer update(@PathVariable Integer id, @RequestBody Customer customer) {
		Customer customer1 = rp.findOne(id);
		if (customer1 != null) {
			customer1.setName(customer.getName());
			customer1.setPhone(customer.getPhone());
			return rp.saveAndFlush(customer1);
		}
		return null;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteId(@PathVariable("id") final int id) {
		rp.delete(Integer.valueOf(id));
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> searchRecords(@Valid @RequestParam("to_search_name") final String toSearchName,
			@Valid @RequestParam("to_search_phone") final String toSearchPhone) {
		return rp.findByNameOrPhone(toSearchName, toSearchPhone);
	}
}
