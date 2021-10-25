<template>
    <v-app :style="{background: $vuetify.theme.themes[theme].background}">
        <app-bar></app-bar>
        <v-main>
            <v-slide-x-transition mode="out-in">
                <router-view/>
            </v-slide-x-transition>
        </v-main>
    </v-app>
</template>

<script>
import AppBar from '@/components/AppBar'

export default {
    components: {
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

