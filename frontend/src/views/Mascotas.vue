<template>
    <v-container>
        <p class="text-h5 mt-4">{{ titulo ? titulo : 'Mis Mascotas' }}</p>
        <v-row
            v-if="mascotas.length"
        >
            <v-col
                v-for="(mascota, index) in mascotas"
                :key="index"
                class="col-12 col-sm-4 col-md-3 col-lg-2"
            >
                <v-card
                    @click="clickMascota(mascota.id)"
                >
                    <v-img
                        aspect-ratio="1"
                        :src="mascota.fotos.length > 0 ? mascota.fotos[0].imagenBase64 : require('@/assets/sin_imagen.jpg')"
                    ></v-img>
                    <v-card
                        flat
                        width="100%"
                        tile
                        dark
                        color="rgba(0,0,0,0.2)"
                        style="position: absolute; bottom: 0px;"
                    >
                        <v-card-text>{{ mascota.nombre }}</v-card-text>
                    </v-card>
                </v-card>
            </v-col>
        </v-row>
        <v-row v-else>
            <v-col>
                <p class="text-h6 my-8 text-center">No tiene ninguna mascota registrada</p>
            </v-col>
        </v-row>
        <v-overlay
            :value="loading"
        >
            <v-progress-circular
                indeterminate
            ></v-progress-circular>
        </v-overlay>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "Mascotas.vue",
    props: {
        goto: Object,
        titulo: String
    },
    data: () => ({
        mascotas: [],
        loading: true
    }),
    methods: {
        getMascotas() {
            this.loading = true;

            let token = this.$store.state.token;

            const request = {
                url: "/api/mascota",
                config: {
                    headers: {
                        'Authorization': `Bearer ${token}`
                    }
                },
                handler: {
                    "200": (response) => {
                        this.mascotas = response.data;
                        console.log(this.mascotas)
                    },
                    "403": () => {
                        this.$router.push({name: "inicio"});
                    },
                    "404": () => {
                        this.mascotas = [];
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
        clickMascota(id) {
            this.goto.params = {
                id: id
            }
            this.$router.push(this.goto);
        }
    },
    created() {
        this.getMascotas();
    }
}
</script>

<style scoped>

</style>