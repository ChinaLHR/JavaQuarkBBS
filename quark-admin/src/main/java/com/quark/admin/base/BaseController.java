package com.quark.admin.base;

import com.quark.admin.dto.QuarkAdminResult;
import com.quark.admin.enums.SqlErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author LHR
 * Create By 2017/8/13
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected QuarkAdminResult restProcessor(ResultProcessor processor){
        QuarkAdminResult result = null;
        try{
            result = processor.process();
        }catch (Exception e){
            logger.error("Sql Error Log :"+e.getMessage());
            result = QuarkAdminResult.error(SqlErrorEnum.RUNTIMEERROR.getErrorMessage());
        }

        return result;
    }
}
