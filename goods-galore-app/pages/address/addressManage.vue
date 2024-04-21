<template>
	<view class="content">
		<view class="row b-b">
			<text class="tit">Name</text>
			<input class="input" type="text" v-model="addressData.name" placeholder="supermarket name" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">Phone</text>
			<input class="input" type="number" v-model="addressData.phoneNumber" placeholder="phone number" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">Post code</text>
			<input class="input" type="number" v-model="addressData.postCode" placeholder="post code" placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">Location</text>
			<text @click="chooseLocation" class="input">
				{{addressData.province}} {{addressData.city}} {{addressData.region}}
			</text>
			<text class="yticon icon-shouhuodizhi" @click="chooseLocation"></text>
		</view>
		<view class="row b-b">
			<text class="tit">Detail Location</text>
			<input class="input" type="text" v-model="addressData.detailAddress" placeholder="Detail Location" placeholder-class="placeholder" />
		</view>

		<view class="row default-row">
			<text class="tit">Set to default</text>
			<switch :checked="addressData.defaultStatus==1" color="#fa436a" @change="switchChange" />
		</view>
		<button class="add-btn" @click="confirm">Submit</button>
	</view>
</template>

<script>
	import {
		addAddress,
		updateAddress,
		fetchAddressDetail
	} from '@/api/address.js';
	export default {
		data() {
			return {
				addressData: {
					name: '',
					phoneNumber: '',
					postCode: '',
					detailAddress: '',
					default: false,
					province: '',
					city: '',
					region: ''
				}
			}
		},
		onLoad(option) {
			let title = 'Add Address';
			if (option.type === 'edit') {
				title = 'Edit Address'
				fetchAddressDetail(option.id).then(response=>{
					this.addressData = response.data;
				});
			}
			this.manageType = option.type;
			uni.setNavigationBarTitle({
				title
			})
		},
		methods: {
			switchChange(e) {
				this.addressData.defaultStatus = e.detail.value ? 1 : 0;
			},
			//地图选择地址
			chooseLocation() {
				uni.chooseLocation({
					success: (data) => {
						this.covertAdderss(data.address);
						this.addressData.detailAddress = data.name;
					}
				})
			},
			//将地址转化为省市区
			covertAdderss(address) {
				console.log("covertAdderss", address);
				if (address.indexOf("Province") != -1) {
					this.addressData.province = address.substr(0, address.indexOf("Province") + 1);
					address = address.replace(this.addressData.province, "");
					this.addressData.city = address.substr(0, address.indexOf("City") + 1);
					address = address.replace(this.addressData.city, "");
					this.addressData.region = address.substr(0, address.indexOf("District") + 1);
				} else {
					this.addressData.province = address.substr(0, address.indexOf("City") + 1);
					address = address.replace(this.addressData.province, "");
					this.addressData.city = "";
					this.addressData.region = address.substr(0, address.indexOf("District") + 1);
				}
			},
			//国外地址更改
			convertAddressForeign(address) {
			    let parts = address.split(","); // 使用逗号分割地址
			    if (parts.length >= 2) {
			        let street = parts[0].trim(); // 获取街道名
			        let city = parts[1].trim(); // 获取城市名
			        return {
			            street: street,
			            city: city
			        };
			    } else {
			        // 如果地址不符合预期格式，返回原始地址或者错误处理
			        return { error: "Invalid address format", originalAddress: address };
			    }
			},
			//提交
			confirm() {
				let data = this.addressData;
				if (!data.name) {
					this.$api.msg('Please input name');
					return;
				}
				if (!/(^1[3|4|5|7|8][0-9]{9}$)/.test(data.phoneNumber)) {
					this.$api.msg('Please input correct phone number');
					return;
				}
				if (!data.province) {
					this.$api.msg('Please choose your location');
					return;
				}
				if (!data.detailAddress) {
					this.$api.msg('Please input detail address');
					return;
				}
				if(this.manageType=='edit'){
					updateAddress(this.addressData).then(response=>{
						//this.$api.prePage()获取上一页实例，可直接调用上页所有数据和方法，在App.vue定义
						this.$api.prePage().refreshList(data, this.manageType);
						this.$api.msg("Address modified successfully!");
						setTimeout(() => {
							uni.navigateBack()
						}, 800)
					});
				}else{
					addAddress(this.addressData).then(response=>{
						//this.$api.prePage()获取上一页实例，可直接调用上页所有数据和方法，在App.vue定义
						this.$api.prePage().refreshList(data, this.manageType);
						this.$api.msg("Add address successfully!");
						setTimeout(() => {
							uni.navigateBack()
						}, 800)
					});
				}
			},
		}
	}
</script>

<style lang="scss">
	page {
		background: $page-color-base;
		padding-top: 16upx;
	}

	.row {
		display: flex;
		align-items: center;
		position: relative;
		padding: 0 30upx;
		height: 110upx;
		background: #fff;

		.tit {
			flex-shrink: 0;
			width: 150upx;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.input {
			flex: 1;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.icon-shouhuodizhi {
			font-size: 36upx;
			color: $font-color-light;
		}
	}

	.default-row {
		margin-top: 16upx;

		.tit {
			flex: 1;
		}

		switch {
			transform: translateX(16upx) scale(.9);
		}
	}

	.add-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		margin: 60upx auto;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
