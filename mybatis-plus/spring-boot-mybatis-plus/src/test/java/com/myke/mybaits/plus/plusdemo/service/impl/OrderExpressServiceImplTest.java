package com.myke.mybaits.plus.plusdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.myke.mybaits.plus.ApplicationTest;
import com.myke.mybaits.plus.plusdemo.entity.OrderExpress;
import com.myke.mybaits.plus.plusdemo.mapper.OrderExpressMapper;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class OrderExpressServiceImplTest extends ApplicationTest {

    @Autowired
    private OrderExpressMapper orderExpressMapper;

    /**
     * express : {"expressCode":"41","expressName":"顺丰-LFXHK","expressNum":"233277540835","expressXname":"龙飞翔-BC-香港"}
     */


    @Test
    public void t1() {


        //QueryWrapper<OrderExpress> orderExpressQueryWrapper = new QueryWrapper<>();
        //OrderExpress orderExpress = new OrderExpress();
        //String orderId = "60116840762091";
        //orderExpress.setOrderId(orderId);
        //orderExpressQueryWrapper.setEntity(orderExpress);
        //
        //OrderExpress orderExpress1 = orderExpressMapper.selectOne(orderExpressQueryWrapper);

        List<OrderExpress> orderExpresses =
                orderExpressMapper.selectList(null);

        RestTemplate restTemplate = new RestTemplate();
        HttpMethod method = HttpMethod.GET;

        HttpHeaders headers = new HttpHeaders();
        String cookie =
                "loginId=3204; manager=%7B%22id%22%3A3204%2C%22managerName%22%3A%22zhangjianbin%22%2C%22trueName%22%3A%22%E7%A0%94%E5%8F%91-%E5%BC%A0%E5%BB%BA%E6%96%8C%22%2C%22moduleId%22%3A%224%2C34%2C37%2C39%22%2C%22managerType%22%3A%223%22%2C%22cid%22%3A%221%22%2C%22icid%22%3Anull%7D; AUTHID=fCaPes%2FFhX79Sl8TfhQyGg0NnB%2BJXjvFT5jeFAfDwZwwx6RayabKw15cEJBYXGSKvPg8rlc0rESE%0Any%2BtyB7yKUj2IyFffs%2FFE0S82X6uIzKAp03po1NheUfrTAMPxsZaVEMUJBR8lFAaWqMf0IUbmlnq%0AjGJ1u98jQMb7hDT6yl8vSe6%2F6Kb7mkQriYBGmfWZOm5ZgM0X56YcRmFz90wULwUVg30eZ%2Br7%2BKWA%0AKGRyNzSunosNMbHyJXNlN0%2BhxGA6cV2FlNVYGIAbkU5bzF0omi61qUYPUflW8%2BKlUlY%2B%2Bp7RZNAS%0AIBpY9eFFxXPYZB0qB4gWMet4JI1ntpgDs7fNQAEUBD0459yIjPBKnCs2bHBmzETq12%2Bes1lvO92o%0AGirxaVUnA0o%2BqeA20bHf3g9kSf1KXxN%2BFDIa2tta2Wl21KOv9mC5zL09F5RYGOIT%2BG0Vp2z%2BuLhi%0AJCdRMjRkgd2NWRiBiOKRzr%2BWZTyIQbwuhCRKjfKpbxsxcbMK%2FVpS1dip%2FUpfE34UMhra21rZaXbU%0Ao0JYryRW8%2FsIv9q46hZrNLJdXI4WQQnqmGlVJwNKPqngsF9JVbbiwThwnHw6BoLdjETaKLR4Dj7t%0ApH%2FZGKKouVp7ldRw2HCXLKFm0dL1JixJ14dqNrenrDF7ldRw2HCXLKeUBPxrT%2BQjRCuJgEaZ9Zk6%0AblmAzRfnplsIMNYmiouTfpzuG0Yky4yUWBjiE%2FhtFSbTzaikzmjXyapE%2F4XJ3N%2F1%2B2bhw3rwRv1K%0AXxN%2BFDIaMip1ARYflevXYH3T7vYqwV9pxAsMwFB3iZvydnYJhLf1jZwQSz2bqNGVHQaXSW9fE0S8%0A2X6uIzJTYimSvP2FnQ%3D%3D; ERPSECOOID=822fb7dec0374e1cad92ccc4492bf6b2";
                headers.put(HttpHeaders.COOKIE, Arrays.asList(cookie));
        HttpEntity request = new HttpEntity(null, headers);

        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, headers);

        for (OrderExpress order : orderExpresses) {
            String orderId = order.getOrderId();
            String url = "http://erp.secoo.com/secooOms/logistics/logisOrder/queryLTByOrderId?orderId=";
            url = url + orderId;
            try {
                ResponseEntity<ExpressD> exchange = restTemplate.exchange(url, method, requestEntity, ExpressD.class);
                ExpressD body = exchange.getBody();
                if (null != body) {
                    OrderExpress orderExpress2 = new OrderExpress();
                    orderExpress2.setUpdateCode(body.getExpress().getExpressNum());
                    Wrapper<OrderExpress> orderExpressWrapper = new UpdateWrapper<OrderExpress>();
                    ((UpdateWrapper<OrderExpress>) orderExpressWrapper).eq("order_id", orderId);
                    int update = orderExpressMapper.update(orderExpress2, orderExpressWrapper);
                    System.out.println("update:" + update);
                }
            } catch (RestClientException e) {
                e.printStackTrace();
            }
        }
    }


    @Data
    public static class ExpressD {
        private ExpressDTO express;
    }

    @Data
    public static class ExpressDTO {
        /**
         * expressCode : 41
         * expressName : 顺丰-LFXHK
         * expressNum : 233277540835
         * expressXname : 龙飞翔-BC-香港
         */

        private String expressCode;
        private String expressName;
        private String expressNum;
        private String expressXname;

        public String getExpressCode() {
            return expressCode;
        }

        public void setExpressCode(String expressCode) {
            this.expressCode = expressCode;
        }

        public String getExpressName() {
            return expressName;
        }

        public void setExpressName(String expressName) {
            this.expressName = expressName;
        }

        public String getExpressNum() {
            return expressNum;
        }

        public void setExpressNum(String expressNum) {
            this.expressNum = expressNum;
        }

        public String getExpressXname() {
            return expressXname;
        }

        public void setExpressXname(String expressXname) {
            this.expressXname = expressXname;
        }
    }
}