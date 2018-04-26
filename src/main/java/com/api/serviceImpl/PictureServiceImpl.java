package com.api.serviceImpl;

import com.api.dao.PictureMapper;
import com.api.model.Picture;
import com.api.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zy on 2018/4/27.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureMapper pictureMapper;

    public Picture queryPictureInfo(long pid) {
        return pictureMapper.selectByPrimaryKey(pid);
    }

}
