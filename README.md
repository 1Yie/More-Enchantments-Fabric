# more-enchantments-template-1.20
#### 学习用
## More Enchantments 更多附魔
- 为 Minecraft Fabric 1.20 添加更多附魔书
## 附魔书 Enchantment Books
### 祝福恢复
- 最高等级为3级
  - 附魔冲突：
    - *嗜血*
- 攻击生物会随机恢复生命值
  - 恢复生命值：1级0.5至1.5；2级1.0至2.0；3级1.5至2.5
- 有概率触发“真理之选择！”
  - 效果为概率恢复所有HP，1级0.01%；2级0.08%；3级0.06%
### 真伤
- 最高等级为4级
- 根据生物的盔甲值和当前附魔伤害值计算，无视护甲值造成相应伤害
- 伤害公式为：总护甲值 * 武器伤害值 * 0.15(每提升一级增加0.05)
  - *可能会有BUG，可能无法正常触发不死图腾效果 ~暂时不会修~*
### 缴械
- 最高等级为1级
- 攻击生物会使生物主手持有的物品掉落
  - 缴械对象是村民时，会掉落双倍的手持物品。*是BUG，但我不会修 ^ ^，~所以是特性~*
### 缓速
- 最高等级为3级
- 攻击生物会给予缓速效果
- 公式为 20 * 2 * level
### 饱食
- 最高等级为1级
- 攻击生物会有五分之一的概率恢复自身一格饱食度
### 嗜血
- 最高等级为3级
- 只能附魔在剑上
- 附魔冲突：
  - *祝福恢复*
- 攻击生物造成的伤害根据等级恢复自身
  - 公式为 造成的伤害 * 等级 * 0.1
### 钻石无处不在
- 最高等级为4级
- 只能附魔在镐子上
- 挖掘部分矿物会有概率掉落钻石
  - 支持的矿物为：
    - 煤矿 | 铁矿 | 红石矿 | 钻石矿 | 青金石矿 | 铜矿
  - 概率为：
    - 一级：40%
    - 二级：30%
    - 三级：20%
    - 四级：10%

## 物品 Items
### 连跳法杖
- 可以合成 (X：幻翼膜；T：金锭；S：木棍；A：无)
  - XTX
  - ASA
  - ASA
- 跳跃后右键即可*~左脚踩右脚飞天！~*
