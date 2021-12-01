<template>
    <v-container>
        <v-row class="mt-4">
            <v-col>
                <v-card>
                    <v-card-title>¡Se notificó correctamente!</v-card-title>
                    <v-card-text>
                        <p>Se le ha avisado al dueño sobre su mascota, ya estan en contacto.</p>
                        <v-btn text color="primary" :to="{name: 'inicio'}">Ir a Inicio</v-btn>
                    </v-card-text>
                    <v-overlay
                        :value="loading"
                        absolute
                        opacity="1"
                    >
                        <v-progress-circular
                            indeterminate
                        ></v-progress-circular>
                    </v-overlay>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
import RequestHelper from "@/utils/RequestHelper";

export default {
    name: "NotificarDuenioMascota",
    props: {
        persona_id: Number,
    },
    data: () => ({
        loading: true
    }),
    methods: {
        notificarDuenio() {
            this.loading = true;

            let id_mascota = this.$route.params.idMascota;
            let id_rescatista = this.persona_id;

            console.log(this.$route.params)

            const request = {
                url: `/api/mascota/${id_mascota}/perdida`,
                body: {
                    id_rescatista: id_rescatista
                },
                handler: {
                    "200": (response) => {
                        console.log(response);
                    },
                    error: (response) => {
                        console.log("error", response.data)
                    },
                    always: () => {
                        this.loading = false;
                    }
                }
            }

            RequestHelper.post(request);
        }
    },
    created() {
        if(!this.persona_id) {
            this.$router.push({
                name: "registrar-rescatista",
                params: {
                    idMascota: this.$route.params.idMascota
                }
            });
        }

        this.notificarDuenio();
    }
}
</script>

<style scoped>

</style>