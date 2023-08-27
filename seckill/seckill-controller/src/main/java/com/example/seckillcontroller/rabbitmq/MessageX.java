package com.example.seckillcontroller.rabbitmq;


import com.example.common.entity.SeckillUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Alias("MiaoshaUser")
public class MessageX {
    private SeckillUser user;
    private long goodsId;
    private int delay;

    public SeckillUser getUser() {
        return user;
    }

    public void setUser(SeckillUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "MessageX{" +
                "user=" + user +
                ", goodsId=" + goodsId +
                ", delay=" + delay +
                '}';
    }
}
