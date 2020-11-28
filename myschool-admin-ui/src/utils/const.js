
const Constants = {
    install(Vue, options) {
        //  create global Constants
        Vue.prototype.$constants = function() {
            return {
                VERSION: "1.0.1",
                BASE_URL: "/api/myschool/",
                USER_ROLES: ["ROLE_TEACHER", "ROLE_SCHOOLADMIN"],
                ROLES: { TEACHER: "ROLE_TEACHER", SADMIN: "ROLE_SCHOOLADMIN"}
            }
        }
        Vue.prototype.restCallHeaders = function () {
            return {'headers': {'Authorization': 'Bearer ' + this.$store.state.user.token}};
        }
    }
};
export default Constants;