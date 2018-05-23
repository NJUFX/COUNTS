import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Worker from '@/components/Worker'
import CountsMarket from '@/components/CountsMarket'
import Requester from '@/components/Requestor'
import NewProject from '@/components/NewProject'
import Admin from '@/components/Admin'
import MyProject from '@/components/MyProject'
import Test from '@/components/Test'
import Test2 from '@/components/Test2'
import Home from '@/components/Home'
import ProjectDetails from '@/components/ProjectDetails'
import CountsDetails from '@/components/CountsDetails'
Vue.use(Router)
export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/:username/worker',
      name: 'Worker',
      component: Worker
    },
    {
      path: '/:username/requester',
      name: 'Requester',
      component: Requester
    },
    {
      path: '/:username/requester/newProject',
      name: 'NewProject',
      component: NewProject
    },
    {
      path: '/:username/myProject',
      name: 'MyProject',
      component: MyProject
    },
    {
      path: '/:username/countsMarket',
      name: 'CountsMarket',
      component: CountsMarket
    },
    {
      path: '/:username/countsMarket/:id/projectDetails',
      name: 'CountsDetails',
      component: CountsDetails
    },
    {
      path: '/:username/admin',
      name: 'Admin',
      component: Admin
    },
    {
      path: '/:username/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/:username/requester/:id/projectDetails',
      name: 'ProjectDetails',
      component: ProjectDetails
    },
    {
      path: '/:username/worker/:id/projectDetails',
      name: 'ProjectDetails',
      component: ProjectDetails
    },
    {
      path: '/:username/test',
      name: 'Test',
      component: Test
    },
    {
      path: '/:username/test2',
      name: 'Test2',
      component: Test2
    }
  ]
})
