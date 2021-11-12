<template>
    <v-app-bar
        app
        color="primary"
        dark
    >
        <v-img 
            max-height="70"
            max-width="70"
            contain
            class="mr-4"
            :src="require('@/assets/logo.png')"
        />
        <v-app-bar-title class="app-bar-logo">
            Patitas
        </v-app-bar-title>
        <v-spacer></v-spacer>
        <AppBarAvatar></AppBarAvatar>
        <template v-slot:extension>
            <AppBarTabs></AppBarTabs>
            <v-spacer></v-spacer>
            <v-btn icon @click="$vuetify.theme.dark = !$vuetify.theme.dark">
                <v-icon>{{ darkIcon }}</v-icon>
            </v-btn>
        </template>
    </v-app-bar>
</template>

<script>
import AppBarTabs from '@/components/AppBarTabs'
import AppBarAvatar from '@/components/AppBarAvatar.vue';

export default {
    components: {
        AppBarTabs,
        AppBarAvatar
    },
    data: () => ({

    }),
    computed: {
        darkIcon() {
            return this.$vuetify.theme.dark ? 'mdi-brightness-7' : 'mdi-brightness-3';
        }
    }
}
</script>

<style lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Pacifico&display=swap');

.app-bar-logo {
    font-family: Pacifico, serif;
}

</style>