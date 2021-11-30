<template>
    <v-tabs background-color="primary">
        <v-tab :to="{name: 'inicio'}">Inicio</v-tab>
        <v-tab :to="{name: 'registrar-mascota-persona'}">Registrar Mascota</v-tab>
        <v-menu
            offset-y
            bottom
            left
            rounded="t-0"
            v-if="logged"
        >
            <template v-slot:activator="{ on, attrs }">
                <v-tab
                    v-bind="attrs"
                    v-on="on"
                >
                    Adopción
                    <v-icon right>
                        mdi-menu-down
                    </v-icon>
                </v-tab>
            </template>

            <v-list>
                <v-list-item link>
                    <v-tab :ripple="false">Adoptar Mascota</v-tab>
                </v-list-item>
                <v-list-item link :to="{name: 'dar-en-adopcion'}">
                    <v-tab :ripple="false">Dar en Adopción</v-tab>
                </v-list-item>
            </v-list>
        </v-menu>
        <v-tab :to="{name: 'registrar-mascota-perdida'}">Encontre Mascota Perdida</v-tab>
        <v-tab :to="{name: 'publicaciones'}">Publicaciones</v-tab>
        <v-tab v-if="rol === 'ROLE_ADMINISTRADOR'">Gestionar Organizaciones</v-tab>
    </v-tabs>
</template>

<script>
export default {
    data: () => ({
        rol: ""
    }),
    methods: {
        chequearRol() {
            let token = this.$store.getters.getToken;

            if(token) {
                this.rol = token.CLAIM_TOKEN[0];
            }
        }
    },
    computed: {
        logged() {
            return !!this.$store.getters.getToken;
        }
    },
    watch: {
        '$store.state.token'() {
            this.chequearRol();
        }
    },
    created() {
        this.chequearRol();
    }
}
</script>

<style>

</style>