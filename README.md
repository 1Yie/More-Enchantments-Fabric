# more-enchantments-template-1.20
#### 学习用
## More Enchantments 更多附魔
- 为 Minecraft Fabric 1.20 添加更多附魔书
## 附魔书 Enchantment Books
### 祝福恢复
- 最高等级为3级
- 攻击生物会随机恢复生命值
- 恢复生命值：1级0.5至1.5；2级1.0至2.0；3级1.5至2.5
- 有概率触发“真理之选择！”，效果为概率恢复所有HP，1级0.01%；2级0.08%；3级0.06%
### 真伤
- 最高等级为4级
- 根据生物的盔甲值和当前附魔伤害值计算，无视护甲值造成相应伤害
- 伤害公式为：总护甲值 * 武器伤害值 * 0.15(每提升一级增加0.05)
- *可能会有BUG，可能无法正常触发不死图腾效果*
### 缴械
- 最高等级为1级
- 攻击生物会使生物主手持有的物品掉落
### 缓速
- 最高等级为3级
- 攻击生物会给予缓速效果
- 公式为 20 * 2 * level
### 饱食
- 最高等级为1级
- 攻击生物会有五分之一的概率恢复自身一格饱食度

## 物品 Items
### 连跳法杖
- 可以合成
- 跳跃后右键即可*~左脚踩右脚飞天！~*