<template>
    <v-container fluid>
        <v-card>
            <v-card-title>Seleccione Organizacion</v-card-title>
            <v-card-text>
                <v-row>
                    <v-col class="col-12 col-sm-6 col-md-4 col-lg-3" v-for="organizacion in organizaciones" :key="organizacion.id">
                        <v-card
                            outlined
                            style="border: 1px solid"
                            @click="seleccionarOrganizacion(organizacion.id)"
                        >
                            <v-card-title>{{ organizacion.nombre }}</v-card-title>
                            <v-card-subtitle>
                                Telefono: {{organizacion.telefono}}<br>
                                Email: {{organizacion.email}}
                            </v-card-subtitle>
                        </v-card>
                    </v-col>
                </v-row>
            </v-card-text>
            <v-overlay
                :value="loading"
                absolute
            >
                <v-progress-circular
                    indeterminate
                ></v-progress-circular>
            </v-overlay>
        </v-card>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "SeleccionarOrganizacion",
    data: () => ({
        loading: false,
        organizaciones: []
    }),
    methods: {
        cargarOrganizaciones() {
            this.loading = true;

            const request = {
                url: "/api/organizacion",
                handler: {
                    "200": (response) => {
                        console.log(response.data);
                        this.organizaciones = response.data;
                    },
                    error: (response) => {
                        console.log("error", response.data);
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            }

            RequestHelper.get(request);
        },
        seleccionarOrganizacion(id) {
            console.log(id);
            this.$emit("OrganizacionSeleccionada", id);
        }
    },
    mounted() {
        this.cargarOrganizaciones();
    }
}
</script>

<style scoped>

</style>