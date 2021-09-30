import { createRouter, createWebHistory } from "vue-router";
import Main from "@/components/MainContent.vue";
import AboutUs from "@/components/about/AboutUs.vue";
import AboutService from "@/components/about/AboutService.vue";

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/search",
    name: "Search",
    component: () => import("../views/Search.vue"),
  },
  {
    path: "/aboutus",
    name: "AboutUs",
    component: AboutUs,
  },
  {
    path: "/aboutService",
    name: "AboutService",
    component: AboutService,
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
