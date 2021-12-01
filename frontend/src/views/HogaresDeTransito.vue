<template>
    <v-container>
        <p class="mt-8 text-h5">Hogares de transito</p>
        <p>Si no tiene un lugar donde dejar la mascota, acá tiene una lista de hogares</p>
        <v-card>
            <v-card-text>

            </v-card-text>
        </v-card>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "HogaresDeTransito",
    props: {
        tamanio: String,
        especie: String,
        caracteristicas: Array,
        posicionGeografica: Object,
    },
    data: () => ({
        loading: false,
        hogares: []
    }),
    methods: {
        getHogares() {
            this.loading = true;

            let request = {
                url: `/api/mascota/hogar?pagina=1`,
                body: {
                    especie: this.especie,
                    tamanio: this.tamanio,
                    posicionGeografica: this.posicionGeografica,
                    caracteristicas: this.caracteristicas,
                    radioMetros: 10000
                },
                handler: {
                    "200": (response) => {
                        console.log(response.data);
                    },
                    default: (response) => {
                        console.log("default", response.data)
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            };

            RequestHelper.post(request);
        }
    },
    created() {
        console.log(this.tamanio);
        console.log(this.especie);
        console.log(this.caracteristicas);
        console.log(this.posicionGeografica);

        if(!this.especie) {
            this.$router.push({name: 'registrar-mascota-perdida'})
            return;
        }

        //this.getHogares();
    }
}
</script>

<style scoped>

</style>