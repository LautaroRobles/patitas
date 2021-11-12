<template>
    <v-container>
        <v-row>
            <v-col>
                <v-card>
                    <v-list
                        dense
                        nav
                    >
                        <v-list-item
                            link
                            v-for="opcion in opciones"
                            :key="opcion.titulo"
                            :to="opcion.to"
                        >
                            <v-list-item-icon>
                                <v-icon>{{ opcion.icon }}</v-icon>
                            </v-list-item-icon>
                            <v-list-item-action>
                                <v-list-item-title>{{ opcion.titulo }}</v-list-item-title>
                            </v-list-item-action>
                        </v-list-item>
                    </v-list>
                </v-card>
            </v-col>
            <v-col>

            </v-col>
        </v-row>
    </v-container>
</template>

<script>
export default {
    name: "ConfiguracionUsuario",
    data: () => ({
        opciones: [
            {
                icon: "mdi-account",
                titulo: "Registrar datos personales",
                to: {name: "registrar-persona"}
            },
            {
                icon: "mdi-cog",
                titulo: "Cambiar contraseña",
                to: {name: "cambiar-contrasenia"}
            }
        ]
    })
}
</script>

<style scoped>

</style>