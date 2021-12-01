<template>
    <v-app :style="{background: $vuetify.theme.themes[theme].background}">
        <AppBar/>
        <v-main>
            <v-slide-x-transition mode="out-in">
                <router-view/>
            </v-slide-x-transition>
        </v-main>
        <Footer/>
    </v-app>
</template>

<script>
import AppBar from '@/components/AppBar'
import Footer from "@/components/Footer";

export default {
    components: {
        Footer,
        AppBar
    },
    data: () => ({
        //
    }),
    computed:{
        theme(){
            return (this.$vuetify.theme.dark) ? 'dark' : 'light'
        }
    }
};
</script>

