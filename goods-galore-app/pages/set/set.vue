<template>
	<view class="container">
		<view class="list-cell b-b m-t" @click="navTo('Personal Details')" hover-class="cell-hover" :hover-stay-time="50">
			<text class="cell-tit">Personal Details</text>
			<text class="cell-more yticon icon-you"></text>
		</view>
		<view class="list-cell b-b" @click="navTo('/pages/address/address')" hover-class="cell-hover" :hover-stay-time="50">
			<text class="cell-tit">Address</text>
			<text class="cell-more yticon icon-you"></text>
		</view>
		<view class="list-cell" @click="navTo('Verification')" hover-class="cell-hover" :hover-stay-time="50">
			<text class="cell-tit">Verification</text>
			<text class="cell-more yticon icon-you"></text>
		</view>
		
		<view class="list-cell m-t">
			<text class="cell-tit">Message receive</text>
			<switch checked color="#fa436a" @change="switchChange" />
		</view>
<!-- 		<view class="list-cell m-t b-b" @click="navTo('clear cache')" hover-class="cell-hover" :hover-stay-time="50">
			<text class="cell-tit">Clear Cache</text>
			<text class="cell-more yticon icon-you"></text>
		</view> -->
		<view class="list-cell b-b" @click="navToOuter('https://github.com/Dante-cjh/fyp-goods-galore')" hover-class="cell-hover" :hover-stay-time="50">
			<text class="cell-tit">About Goods Galore</text>
			<text class="cell-more yticon icon-you"></text>
		</view>
		<view class="list-cell">
			<text class="cell-tit">Check Update</text>
			<text class="cell-tip">Version 1.0.0</text>
			<text class="cell-more yticon icon-you"></text>
		</view>
		<view class="list-cell log-out-btn" @click="toLogout">
			<text class="cell-tit">Logout</text>
		</view>
	</view>
</template>

<script>
	import {  
	    mapMutations  
	} from 'vuex';
	export default {
		data() {
			return {
				
			};
		},
		methods:{
			...mapMutations(['logout']),

			navTo(url){
				if(url.indexOf("pages")!=-1){
					uni.navigateTo({
						url:url
					});
				}
				this.$api.msg(`Jump to ${url}`);
			},
			navToOuter(url){
				window.location.href = url;
			},
			//退出登录
			toLogout(){
				uni.showModal({
				    content: 'Confirm to logout?',
				    success: (e)=>{
				    	if(e.confirm){
				    		this.logout();
				    		setTimeout(()=>{
				    			uni.navigateBack();
				    		}, 200)
				    	}
				    }
				});
			},
			//switch
			switchChange(e){
				let statusTip = e.detail.value ? 'Open': 'Close';
				this.$api.msg(`${statusTip} Message Push`);
			},

		}
	}
</script>

<style lang='scss'>
	page{
		background: $page-color-base;
	}
	.list-cell{
		display:flex;
		align-items:baseline;
		padding: 20upx $page-row-spacing;
		line-height:60upx;
		position:relative;
		background: #fff;
		justify-content: center;
		&.log-out-btn{
			margin-top: 40upx;
			.cell-tit{
				color: $uni-color-primary;
				text-align: center;
				margin-right: 0;
			}
		}
		&.cell-hover{
			background:#fafafa;
		}
		&.b-b:after{
			left: 30upx;
		}
		&.m-t{
			margin-top: 16upx; 
		}
		.cell-more{
			align-self: baseline;
			font-size:$font-lg;
			color:$font-color-light;
			margin-left:10upx;
		}
		.cell-tit{
			flex: 1;
			font-size: $font-base + 2upx;
			color: $font-color-dark;
			margin-right:10upx;
		}
		.cell-tip{
			font-size: $font-base;
			color: $font-color-light;
		}
		switch{
			transform: translateX(16upx) scale(.84);
		}
	}
</style>
