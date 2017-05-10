package grp3022.bbs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import grp3022.bbs.dao.HospitalOrderDao;
import grp3022.bbs.dao.HospitalOrderExtDao;
import grp3022.bbs.po.HospitalOrder;
import grp3022.bbs.po.HospitalOrderExample;
import grp3022.bbs.so.HospitalOrderSo;



@Service
public class HospitalOrderServiceImpl implements HospitalOrderService {

	@Autowired
    private HospitalOrderDao hospitalOrderDao;

    @Autowired
    private HospitalOrderExtDao hospitalOrderExtDao;
    
	@Override
	public HospitalOrder getRecordById(Long id) {
		HospitalOrder record = new HospitalOrder();
        record = hospitalOrderDao.selectByPrimaryKey(id);
        return record;
	}

	@Override
	public List<HospitalOrder> getAllRecords() {
		HospitalOrderExample uie = new HospitalOrderExample();
        List<HospitalOrder> records = hospitalOrderDao.selectByExample(uie);
        return records;
	}

	@Override
	public int getRecordsCount() {
		HospitalOrderExample uie = new HospitalOrderExample();
        int count = hospitalOrderDao.countByExample(uie);
        return count;
	}

	@Override
	public Long addRecord(HospitalOrder ho) {
		ho.setCreateTime(new Date());
		ho.setUpdateTime(new Date());
		hospitalOrderExtDao.add(ho);
		System.out.println("当前生成的订单id为："+ho.getId());
		return ho.getId();
	}

	@Override
	public void deleteRecord(Long id) {
		hospitalOrderDao.deleteByPrimaryKey(id);

	}

	@Override
	public void updateRecordById(HospitalOrder hospitalOrder) {
		hospitalOrderDao.updateByPrimaryKeySelective(hospitalOrder);
	}

	@Override
	public PageInfo<HospitalOrder> getPageBySo(HospitalOrderSo so, Integer pageNo,Integer size) {
		pageNo = pageNo == null?1:pageNo;
		size = size == null?10:size;
        PageHelper.startPage(pageNo, size);
        List<HospitalOrder> records = hospitalOrderExtDao.selectBySo(so);
        PageInfo<HospitalOrder> page = new PageInfo<HospitalOrder>(records);
        if(page.getPageNum()>page.getPages())
        	page.setPageNum(page.getPages());
        return page;
	}

	@Override
	public List<HospitalOrder> getRecordsByIds(List<Long> ids) {
		List<HospitalOrder> records = new ArrayList<HospitalOrder>();
		for (Long id : ids) { 
            HospitalOrder record = hospitalOrderDao.selectByPrimaryKey(id);
            records.add(record);
        } 
        return records;
	}

	@Override
	public PageInfo<HospitalOrder> getPaidBySo(HospitalOrderSo so, Integer pageNo, Integer size) {
		pageNo = pageNo == null?1:pageNo;
		size = size == null?10:size;
        PageHelper.startPage(pageNo, size);
        
        HospitalOrderExample example = new HospitalOrderExample();
        example.or().andPatientIdEqualTo(so.getPatientId()).andStatusBetween((short)20, (short)30);
        example.setOrderByClause("pay_time desc");
        List<HospitalOrder> records = hospitalOrderDao.selectByExample(example);
        PageInfo<HospitalOrder> page = new PageInfo<HospitalOrder>(records);
        return page;
	}

}
