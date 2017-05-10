package grp.bbs.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import grp3022.bbs.po.HospitalOrder;
import grp3022.bbs.so.HospitalOrderSo;

public interface HospitalOrderService {
	HospitalOrder getRecordById(Long id);
	/*	读取所有  */
	List<HospitalOrder> getAllRecords();
	/*	读取数量  */
	int getRecordsCount();
	/*	增  */
	Long addRecord(HospitalOrder ho);
	/*	删  */
	void deleteRecord(Long id);
	/*	改  */
	void updateRecordById(HospitalOrder hospitalOrder);
	/*	查  */
	PageInfo<HospitalOrder> getPageBySo(HospitalOrderSo hospitalOrderSo,Integer pageNo,Integer size);
	/*  根据id集合获得对应记录列表 */
	List<HospitalOrder> getRecordsByIds(List<Long> ids);
	/*	根据so获得已支付的记录  */
	PageInfo<HospitalOrder> getPaidBySo(HospitalOrderSo hospitalOrderSo,Integer pageNo,Integer size);
}
