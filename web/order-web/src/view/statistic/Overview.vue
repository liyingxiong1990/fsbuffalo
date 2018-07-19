<template>
  <div class="stats-province-overview">
    <div class="planar">
      <div class="planar-box">
        <div class="planar-box-title">
          企业总数
        </div>
        <div class="planar-box-content">
          {{data.companyNum}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          会员总数
        </div>
        <div class="planar-box-content">
          {{data.memberNum}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          挂牌企业家数
        </div>
        <div class="planar-box-content">
          {{data.listingNum}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          本期增加挂牌企业
        </div>
        <div class="planar-box-content">
          {{data.increaseNum}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          本期减少挂牌企业
        </div>
        <div class="planar-box-content">
          {{data.decreaseNum}}
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          总股本
        </div>
        <div class="planar-box-content">
          {{(data.totalCapital/10000).toFixed(2)}} 万元
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          总资产
        </div>
        <div class="planar-box-content">
          {{(data.totalAssets/10000).toFixed(2)}} 万元
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          营业收入
        </div>
        <div class="planar-box-content">
          {{(data.totalIncome/10000).toFixed(2)}} 万元
        </div>
      </div>
      <div class="planar-box">
        <div class="planar-box-title">
          净利润
        </div>
        <div class="planar-box-content">
          {{(data.netProfit/10000).toFixed(2)}} 万元
        </div>
      </div>
      <div style="clear:both"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'statsOverview', // 概况
  props: {
  },
  created () {
    this.initData()
  },
  computed: {
    dictType () {
      return this.$store.getters.sysDict('statistic_role_type')
    }
  },
  data () {
    return {
      data: {
        companyNum: 0,
        memberNum: 0,
        listingNum: 0,
        increaseNum: 0,
        decreaseNum: 0,
        totalCapital: 0,
        totalAssets: 0,
        totalIncome: 0,
        netProfit: 0
      }
    }
  },
  methods: {
    initData () {
      this.companyNum()
      this.memberNum()
      this.listingNum()
      this.increaseNum()
      this.decreaseNum()
      this.totalCapital()
      this.totalAssets()
      this.totalIncome()
      this.netProfit()
    },
    companyNum () {
      this.$store.state.http.auto('bossStatistic', 'companyNum', {}).then(res => {
        this.data.companyNum = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    memberNum () {
      this.$store.state.http.auto('bossStatistic', 'memberNum', {}).then(res => {
        this.data.memberNum = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    listingNum () {
      this.$store.state.http.auto('bossStatistic', 'listingNum', {}).then(res => {
        this.data.listingNum = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    increaseNum () {
      this.$store.state.http.auto('bossStatistic', 'increaseNum', {}).then(res => {
        this.data.increaseNum = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    decreaseNum () {
      this.$store.state.http.auto('bossStatistic', 'decreaseNum', {}).then(res => {
        this.data.decreaseNum = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    totalCapital () {
      this.$store.state.http.auto('bossStatistic', 'totalCapital', {}).then(res => {
        this.data.totalCapital = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    totalAssets () {
      this.$store.state.http.auto('bossStatistic', 'totalAssets', {}).then(res => {
        this.data.totalAssets = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    totalIncome () {
      this.$store.state.http.auto('bossStatistic', 'totalIncome', {}).then(res => {
        this.data.totalIncome = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    },
    netProfit () {
      this.$store.state.http.auto('bossStatistic', 'netProfit', {}).then(res => {
        this.data.netProfit = res.data
      }).catch(error => {
        this.$message.error(error.statusText)
        this.dialog.loading = false
        console.log(error)
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
.stats-province-overview {
  width: 100%;
  padding: 20px 50px;
  box-sizing: border-box;
  .planar {
    width: 100%;
    box-sizing: border-box;
    .planar-box {
      float: left;
      width: 260px;
      margin: 0 30px 10px;
      border-bottom: 1px solid #e4e7ed;
      .planar-box-title {
        margin-bottom: 10px;
        font-weight: 700;
      }
      .planar-box-content {
        margin-bottom: 10px;
        color: #9c9ea2;
      }
    }
  }
}
</style>

<style lang="scss">
</style>