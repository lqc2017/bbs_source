package grp3022.bbs.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.HospitalOrder;
import grp3022.bbs.service.HospitalOrderService;
import grp3022.bbs.so.HospitalOrderSo;

@Controller
@RequestMapping("/order")
public class HospitalOrderController {

	@Resource
	private HospitalOrderService hospitalOrderService;
	private final String pathPrefix = "order";

	/*
	 * 订单列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView orderList(Integer pn, @ModelAttribute("so") HospitalOrderSo so) {
		PageInfo<HospitalOrder> pageInfo = hospitalOrderService.getPageBySo(so,pn,null);
		ModelAndView mav = new ModelAndView(pathPrefix + "/list");
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("so", so);
		return mav;
	}

	/*private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}*/
}
