package hr.manage.service.impl.System_examine;

import hr.manage.dao.System_examine.Stm_examineDao;
import hr.manage.entity.System_Review;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("Stm_Service")
public class Stm_examineImpl {

    @Resource
    private Stm_examineDao stm_examineDao;

    @Transactional
    public void Examine(){

        String ids="";
        //输出日志
        Logger logger =Logger.getLogger(Stm_examineImpl.class);
        List<System_Review> System_Review_list = stm_examineDao.findRecoreId();
        
        if (System_Review_list.size()!=0){

		        for (int i = 0; i <System_Review_list.size() ; i++) {
		            Integer id=System_Review_list.get(i).getRecoreId();
		            //打印日志
		            String logString = setLog(System_Review_list.get(i));
		            logger.fatal(logString);
		            if(i!=System_Review_list.size()-1){
		                ids+=(id+",");
		            }else{
		                ids+=(id);
		            }
		        }
                 System.out.println(ids);
                  int i=stm_examineDao.UpdateState(ids);
                  System.out.println(i);
        }else {
        	Date date=new Date();
        	String dateFormat=
        			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date).toString();
            logger.fatal(dateFormat+"没有要替换的数据");
        }
       
    }

    //设置日志样式
    private String setLog(System_Review system_review){
        Integer id=system_review.getRecoreId();
        Date creatTime=system_review.getCreationDate();
        Integer day=-(system_review.getOut_Time());
        Integer checkstatus=system_review.getCheckstatus();
        String status=null;
        if(checkstatus==1){
            status="创建";
        }else if (checkstatus==2){
            status="拒绝";
        }else if (checkstatus==3){
            status="审核通过";
        }else if (checkstatus==4){
            status="驳回";
        }else if (checkstatus==5){
            status="终止";
        }else if (checkstatus==6){
            status="完成";
        }
        if (day!=0){
            //在系统崩掉情况下，修改过期备份
            return "系统监测：备份ID为"+id+",创建时间为"+creatTime+",的备份已逾期"+day+"天。并将状态"+status+"修改为逾期";
        }
        return "系统监测：备份ID为"+id+",创建时间为"+creatTime+",的备份到期。并将状态"+status+"修改为逾期";
    }
}
