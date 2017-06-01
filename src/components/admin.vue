  <template>
    <div id="home">
      <Headers></Headers>
      <Navigate></Navigate>
      <!--搜索框-->
      <el-col :span="13" :xs="8" class="searchPos">
        <div class="grid-content bg-purple-light">
          <el-input placeholder="请输入你要寻找的内容..." id="sKey" v-model="filtersKey" @change="fuzzyQuery(filtersKey)">
            <el-button slot="append" class="btn" icon="search" @click="searchPerson" v-if="this.filtersKey === ''">
            </el-button>
            <el-button slot="append" class="btn" icon="circle-cross" v-else>
            </el-button>
          </el-input>
        </div>
      </el-col>
      <!--添加按钮 点击触发dialog-->
      <!--<el-col :span="5" :xs="5" style="background: #e5e9f2" class="pos">
        <div class="grid-content bg-purple-light" style="float: right">
          <el-button type="primary" @click="openDialog" icon="edit">
            Add
          </el-button>
        </div>
      </el-col>-->

      <el-col :span="3" :xs="3" class="user">
        <el-dropdown @command="loginOut" style="float: right; padding-right: 20px;color: #324157">
          <el-button type="primary">
            Hello,{{user.name}}<i class="el-icon-caret-bottom el-icon--right"></i>
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command>登出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
      <!--侧栏图片-->
     <!--  <el-col :span="4" v-for="(o, index) in 1" :key="o" :offset="index > 0 ? 1 : 0" class="side-img">
        <el-card :body-style="{ padding: '0px' }">
          <img src="https://oc1gyfe6q.qnssl.com/XqjPCz.jpg?raw=true" class="image">
          <div style="padding: 14px;">
            <span>联系人</span>
            <div class="bottom clearfix">
              <time class="time">{{ currentDate }}</time>
            </div>
          </div>
        </el-card>
      </el-col> -->
      <!--Table展示数据-->
      <!--<el-col :span="22" :xs="22" :offset="1">
        <el-table :data="contacts">
          <el-table-column type="expand">
            <template scope="props">
              <p>
                家庭电话: {{ props.row.homeNumber }}
              </p>
              <p>
                生日：{{ props.row.birthday }}
              </p>
              <p>
                分组：{{ props.row.battery }}
              </p>
              <p>
                地址：{{ props.row.address }}
              </p>
              <p>
                个人主页：<a :href="props.row.site" target="_blank"> {{ props.row.site }} </a>
              </p>
            </template>
          </el-table-column>
          <el-table-column label="姓名" prop="name" width="150">
          </el-table-column>
          <el-table-column label="邮箱" prop="email">
          </el-table-column>
          <el-table-column label="电话" prop="phoneNumber">
          </el-table-column>
          <el-table-column prop="battery" label="分组" width="100" :filters="groups"
          :filter-method="filterTag">
            <template scope="scope">
              <el-tag :type="scope.row.battery === '家' ? 'primary' : 'success'" close-transition>
                {{scope.row.battery}}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" @click="editPerson(scope.$index, scope.row)">
                Edit
              </el-button>
              <el-button size="small" type="danger" @click="delPerson(scope.$index, scope.row)">
                Del
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>-->
      <el-col :span="22" :xs="22" :offset="1">
      <el-tabs type="border-card">
        <el-tab-pane label="通过审核">
            <h2>通过审核</h2>
            <el-table :data="okperson" :default-sort = "{prop: 'lastLoginTime', order: 'descending'}">
              <el-table-column label="帐号" prop="username" width="150">
              </el-table-column>
              <el-table-column label="最后登录时间" prop="lastLoginTime" :formatter="formatter" sortable>
              </el-table-column>
              <el-table-column label="登录次数" prop="loginCount">
              </el-table-column>
              <el-table-column label="操作">
                <template scope="scope">
                  <el-button size="small" type="danger" @click="notPerson(scope.$index, scope.row)">
                    禁用
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
            <el-col :span="22">
              <div class="block" style="text-align:center;margin-top:100px">
                <el-pagination
                  layout="prev, pager, next"
                  :total="1">
                </el-pagination>
              </div>
            </el-col> 
        </el-tab-pane>

        <el-tab-pane label="等待审核">
        <h2>等待审核</h2>
        <el-table :data="noperson">
          <el-table-column label="帐号" prop="username" width="150">
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" @click="passPerson(scope.$index, scope.row)">
                通过
              </el-button>
             <!-- <el-button size="small" type="danger" @click="delPerson(scope.$index, scope.row)">
                拒绝
              </el-button> -->
              <el-button size="small" type="danger" @click="delPerson(scope.$index, scope.row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        </el-tab-pane>

        <el-tab-pane label="禁用账户">
          <h2>禁用账户</h2>
          <el-table :data="goperson">
            <el-table-column label="帐号" prop="username" width="150">
            </el-table-column>
            <el-table-column label="操作">
              <template scope="scope">
                <el-button size="small" @click="passPerson(scope.$index, scope.row)">
                  恢复
                </el-button>
                <el-button size="small" type="danger" @click="delPerson(scope.$index, scope.row)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
      </el-col>

    <!--  <el-col :span="22" :xs="22" :offset="1">
        <h2>通过审核</h2>
        <el-table :data="okperson" :default-sort = "{prop: 'lastLoginTime', order: 'descending'}">
          <el-table-column label="帐号" prop="username" width="150">
          </el-table-column>
          <el-table-column label="最后登录时间" prop="lastLoginTime" :formatter="formatter" sortable>
          </el-table-column>
          <el-table-column label="登录次数" prop="loginCount">
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" type="danger" @click="notPerson(scope.$index, scope.row)">
                禁用
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="11" :xs="22" :offset="1">
        <h2>等待审核</h2>
        <el-table :data="noperson">
          <el-table-column label="帐号" prop="username" width="150">
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" @click="passPerson(scope.$index, scope.row)">
                通过
              </el-button>
              <el-button size="small" type="danger" @click="delPerson(scope.$index, scope.row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>

      <el-col :span="11" :xs="22" :offset="1">
        <h2>禁用帐号</h2>
        <el-table :data="goperson">
          <el-table-column label="帐号" prop="username" width="150">
          </el-table-column>
          <el-table-column label="操作">
            <template scope="scope">
              <el-button size="small" @click="passPerson(scope.$index, scope.row)">
                恢复
              </el-button>
              <el-button size="small" type="danger" @click="delPerson(scope.$index, scope.row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col> -->

      <!--dialog模拟框添加数据-->
     <!-- <el-dialog title="添加联系人" v-model="dialogVisible" size="small">
        <el-form :model="form" :rules="rules" ref="from" :label-position="labelPosition"
        label-width="100px" class="add-form">
          <el-form-item label="姓名" required>
            <el-input v-model="form.name" auto-complete="off" placeholder="请输入该联系人的姓名">
            </el-input>
          </el-form-item>
          <el-form-item label="电子邮箱" prop="email">
            <el-input v-model="form.email" auto-complete="off" placeholder="请输入该联系人的邮箱">
            </el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phoneNumber">
            <el-input v-model="form.phoneNumber" auto-complete="off" placeholder="请输入该联系人的手机号码">
            </el-input>
          </el-form-item>
          <el-form-item label="家庭电话" prop="homeNumber" required>
            <el-input v-model.number="form.homeNumber" auto-complete="off" placeholder="选填项">
            </el-input>
          </el-form-item>
          <el-form-item label="生日" prop="birthday" required>
            <el-input type="date" format="yyyy-MM-dd" placeholder="选择日期" v-model="form.birthday">
            </el-input>
          </el-form-item>
          <el-form-item label="个人主页" prop="site" required>
            <el-input v-model="form.site" auto-complete="off"  placeholder="选填项">
            </el-input>
          </el-form-item>
          <el-form-item>
          </el-form-item>
          <el-form-item label="分组" prop="battery" required>
            <el-input v-model="form.battery" placeholder="选填项">
            </el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address" required>
            <el-input type="textarea" v-model="form.address" style="width: 70%;" auto-complete="off" placeholder="请输入地址">
            </el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible=false" v-if="sure">
            取 消
          </el-button>
          <el-button @click="dialogVisible=false" type="info" v-else>
            取 消
          </el-button>
          <el-button type="primary" @click="addPerson" v-if="sure">
            确 定
          </el-button>
          <el-button type="warning" @click="updatePerson" v-else>
            修 改
          </el-button>
        </span>
      </el-dialog> -->
    </div>
  </template>
  <script>
    import Headers from '../components/Headers.vue'
    import Navigate from '../components/Navigate.vue'
    import {mapActions,mapGetters} from 'vuex'
    const qs = require('qs')
    export default {
    name: 'home',
    data() {
      return {
        groups: [],
        tempContacts: [],
        dialogVisible: false,
        dialogFormVisible: false,
        labelPosition: 'left',
        sure: 'true',
        focusStatus: false,
        currentForm: {},
        currentIndex: '',
        filtersKey: '',
        currentDate: new Date(),
        okperson: [],
        noperson: [],
        goperson: [],
        tempObj: {},
        user: {
          name: ''
        },
        form: {
          name: '',
          email: '',
          phoneNumber: '',
          homeNumber: '',
          birthday: '',
          address: '',
          site: '',
          battery: ''
        },
        rules: {
          email: [
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
          ],
          phoneNumber: [
            { required: true, message: '请填写手机号码', trigger: 'blur' },
          ]
        }
      }
    },
    components: {
      Headers,
      Navigate
    },
    computed: {
      ...mapGetters({
        contacts: 'allContacts'
      })
    },
    beforeCreate(){
      // 当主页刷新时，如果服务端设置的token
      // 的时效到了的话，便会提示未登录
      if(localStorage.getItem('permission') != 1){
        this.$message.error("无权限进入");
        this.$router.push('/')
      }
      this.$http.get('http://localhost:8081/user/token/'+localStorage.getItem('token'))
        .then(res => {
          if (!res.data.success) {
            this.userLoginOut();
            this.$message.error(res.data.error);
            this.user.name = null;
            return false;
          }else{
            let username = localStorage.getItem('username');
            if (username) {
              this.user.name = username;
            }
          }
        })
        .catch(err => {
            this.$message.error(`${err.message}`)
        })
    },
    created() {
      this.$store.dispatch('GET_PERSON')
      this.$store.dispatch('GET_GROUP')
      this.$http.get("http://127.0.0.1:8081/passedusers").then((res) =>
        this.okperson = res.data
      )
      this.$http.get("http://127.0.0.1:8081/waitusers").then((res) =>
        this.noperson = res.data
      )
      this.$http.get("http://127.0.0.1:8081/forbiddenusers").then((res) =>
        this.goperson = res.data
      )
    },
    methods: {
      ...mapActions(['userLoginOut']),
      loginOut(){
        this.userLoginOut();
        this.user.name = null;
        if (!this.$store.state.token) {
            this.$router.push('/login')
            this.$message.success('登出成功');
        } else {
            this.$message.success('登出失败');
        }
      },
      // 点击Add打开Dialog 并清空上一次的数据
      ...mapActions(['userLoginOut']),
      openDialog() {
        this.dialogVisible = true
        this.sure = true
        for (let value in this.form) {
          this.form[value] = ''
        }
      },

      searchPerson() {
        let input = document.querySelector('#sKey')
        input.children[0].focus()
        this.$store.dispatch('GET_PERSON')
        this.filtersKey = ''
      },



      //通过审查
      passPerson(index,row){
          this.$confirm('是否通过此联系人审核, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(row.id)
          this.$http.put("http://127.0.0.1:8081/user/"+row.id+"/verify=1").then((res) =>
              this.$message({
              type: 'success',
              message: '审核通过!'
          })
          )
          this.$router.go("/admin")          
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消'
          }); 
        });
      },
      //禁用
      notPerson(index,row){
          this.$confirm('是否禁用此联系人, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(row.id)
          this.$http.put("http://127.0.0.1:8081/user/"+row.id+"/verify=2").then((res) =>
              this.$message({
              type: 'success',
              message: '禁用成功!'
          })
          )
          this.$router.go("/admin")
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消'
          }); 
        });
      },
      // 删除一行数据
      delPerson(index, row) {
        this.$confirm('此操作将永久删除该联系人, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.delete("http://127.0.0.1:8081/notpasseduser/"+row.id).then((res) =>
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          )
          // this.noperson.splice(index, 1)
          this.$router.go("/admin")
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          }); 
        });
        
      },

      //编辑一行数据
      editPerson(index, row) {
        this.sure = false
        this.dialogVisible = true
        this.form = this.deepCopy(row)
        this.tempObj = this.deepCopy(row)
        this.currentIndex = index
      },

      // 修改一行数据
      updatePerson() {
        for (let k = 0; k < this.contacts.length; k++) {
          if (typeof this.contacts[k]['index'] === 'undefined') {
            this.$set(this.contacts[k], 'index', k)
          }
        }
        for (let i = 0; i < this.contacts.length; i++) {
          // 根据主键查找要修改的数据，然后将this.form数据更新到this.contacts[i]
          if (this.contacts[i]['index'] === this.currentIndex) {
            for (let j in this.form) {
              this.contacts[i][j] = this.form[j]
            }
            break;
          }
        }
        this.$store.dispatch('UPDATE_PERSON', this.form)
       
        this.dialogVisible = false
        this.form = {}
      },

      formatter(row, column) {
        return (new Date(row.lastLoginTime)).toLocaleString()
      },
      // 对象深拷贝
      deepCopy(p, c) {
        c = c || {};
        for (var i in p) {
          // 属性i是否为p对象的自有属性
          if (p.hasOwnProperty(i)) {
            // 属性i是否为复杂类型
            if (typeof p[i] === 'object') {
              // 如果p[i]是数组，则创建一个新数组
              // 如果p[i]是普通对象，则创建一个新对象
              c[i] = Array.isArray(p[i]) ? [] : {};
              // 递归拷贝复杂类型的属性
              this.deepCopy(p[i], c[i]);
            } else {
              // 属性是基础类型时，直接拷贝
              c[i] = p[i];
            }
          }
        }
        return c;
      },

      // 分组过滤method
      filterTag(value, row) {
        return row.battery === value;
      },
      fuzzyQuery(key) {
        this.$store.dispatch('FUZZY_QUERY',key)
      }
    }
  }
  </script>
  <style lang="scss">
  // common
   .pos {
      position: absolute;
      top: 0;
      right: 0;
    }
    .searchPos {
      position: absolute;
      top: 0;
      right: 20.833333%
    }
    .user {
      position: absolute;
      top: 71px;
      right: 0;
      height: 36px;
      line-height: 36px;
    }
    .side-img {
      padding-left: 30px;
    }
    #home {
    .add-form {
      display: flex;
      justify-content: center;
      flex-direction: column;
      text-align: center;
      label {
        padding-left: 25px !important;
        padding-right: 0px !important;
      }
      .el-form-item__error {
        left: 15% !important;
      }
    }
    .time {
      font-size: 13px;
      color: #999;
    }
    
    .bottom {
      margin-top: 13px;
      line-height: 12px;
    }

    .button {
      padding: 0;
      float: right;
    }

    .image {
      width: 100%;
      display: block;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    
    .clearfix:after {
        clear: both
    }
  }
  </style>