pipeline {
        agent any
        stages {      
            stage("Test"){
                steps {
                    sshagent (credentials: ['ssh-machinedevops'] ){ 
                        sh '''
                        pwd
                        cat /var/lib/jenkins/.ssh/config
                        '''
                    }
                }
            }      
            stage("Running main playbook for docker deployment"){
                steps {
                    sshagent (credentials: ['ssh-machinedevops'] ){ 
                        sh '''
                        ansible-playbook /var/lib/jenkins/workspace/AnsibleJob/kubernetes-deploy/playbooks/mainPlaybook.yaml -i /var/lib/jenkins/workspace/AnsibleJob/kubernetes-deploy/hosts -e targetMachine=20.224.66.212
                        '''
                    }
                }
            }
            
        }
}
//test6
