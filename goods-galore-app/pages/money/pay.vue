<template>
	<view class="app">
		<view class="price-box">
			<text>Pay Amount</text>
			<text class="price">{{orderInfo.payAmount}}</text>
		</view>

		<view class="pay-type-list">

			<view class="type-item b-b" @click="changePayType(2)">
				<text class="icon yticon icon-weixinzhifu"></text>
				<view class="con">
					<text class="tit">WeChat Pay</text>
					<text>Recommend WeChat Pay</text>
				</view>
				<label class="radio">
					<radio value="" color="#fa436a" :checked='payType == 2' />
					</radio>
				</label>
			</view>
			<view class="type-item b-b" @click="changePayType(1)">
				<text class="icon yticon icon-alipay"></text>
				<view class="con">
					<text class="tit">Ali Pay</text>
				</view>
				<label class="radio">
					<radio value="" color="#fa436a" :checked='payType == 1' />
					</radio>
				</label>
			</view>
		</view>

		<text class="mix-btn" @click="confirm">Confirm to pay</text>
	</view>
</template>

<script>
	import {
		fetchOrderDetail,
		payOrderSuccess
	} from '@/api/order.js';
	export default {
		data() {
			return {
				orderId: null,
				payType: 2,
				orderInfo: {}
			};
		},
		onLoad(options) {
			this.orderId = options.orderId;
			fetchOrderDetail(this.orderId).then(response => {
				this.orderInfo = response.data;
			});
		},
		methods: {
			//选择支付方式
			changePayType(type) {
				this.payType = type;
			},
			//确认支付
			confirm: async function() {
				payOrderSuccess({
					orderId: this.orderId,
					payType: this.payType
				}).then(response => {
					uni.redirectTo({
						url: '/pages/money/paySuccess'
					})
				});
			},
		}
	}
</script>

<style lang='scss'>
	.app {
		width: 100%;
	}

	.price-box {
		background-color: #fff;
		height: 265upx;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		font-size: 28upx;
		color: #909399;

		.price {
			font-size: 50upx;
			color: #303133;
			margin-top: 12upx;

			&:before {
				content: '￥';
				font-size: 40upx;
			}
		}
	}

	.pay-type-list {
		margin-top: 20upx;
		background-color: #fff;
		padding-left: 60upx;

		.type-item {
			height: 120upx;
			padding: 20upx 0;
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding-right: 60upx;
			font-size: 30upx;
			position: relative;
		}

		.icon {
			width: 100upx;
			font-size: 52upx;
		}

		.icon-erjiye-yucunkuan {
			color: #fe8e2e;
		}

		.icon-weixinzhifu {
			color: #36cb59;
		}

		.icon-alipay {
			color: #01aaef;
		}

		.tit {
			font-size: $font-lg;
			color: $font-color-dark;
			margin-bottom: 4upx;
		}

		.con {
			flex: 1;
			display: flex;
			flex-direction: column;
			font-size: $font-sm;
			color: $font-color-light;
		}
	}

	.mix-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 630upx;
		height: 80upx;
		margin: 80upx auto 30upx;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
