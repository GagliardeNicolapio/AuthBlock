package com.example.authblock;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class Contracts_AuthBlockFull_sol_AuthBlockFull extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506105b8806100206000396000f3fe608060405234801561001057600080fd5b506004361061002b5760003560e01c80631406576914610030575b600080fd5b61004361003e3660046102f6565b610045565b005b833b1580156100535750823b155b61005c57600080fd5b6001600160a01b0384166000908152602081815260408220805460018101825590835291208351849260050290910190819061009890826104c2565b50602082015160018201906100ad90826104c2565b50604082015160028201906100c290826104c2565b50606082015160038201906100d790826104c2565b50608082015160048201906100ec90826104c2565b5050506001600160a01b038316600090815260016020818152604083208054928301815583529091208251839260030290910190819061012c90826104c2565b506020820151600182019061014190826104c2565b506040820151600282019061015690826104c2565b50505050505050565b80356001600160a01b038116811461017657600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b60405160a0810167ffffffffffffffff811182821017156101b4576101b461017b565b60405290565b600082601f8301126101cb57600080fd5b813567ffffffffffffffff808211156101e6576101e661017b565b604051601f8301601f19908116603f0116810190828211818310171561020e5761020e61017b565b8160405283815286602085880101111561022757600080fd5b836020870160208301376000602085830101528094505050505092915050565b60006060828403121561025957600080fd5b6040516060810167ffffffffffffffff828210818311171561027d5761027d61017b565b81604052829350843591508082111561029557600080fd5b6102a1868387016101ba565b835260208501359150808211156102b757600080fd5b6102c3868387016101ba565b602084015260408501359150808211156102dc57600080fd5b506102e9858286016101ba565b6040830152505092915050565b6000806000806080858703121561030c57600080fd5b6103158561015f565b93506103236020860161015f565b9250604085013567ffffffffffffffff8082111561034057600080fd5b9086019060a0828903121561035457600080fd5b61035c610191565b82358281111561036b57600080fd5b6103778a8286016101ba565b82525060208301358281111561038c57600080fd5b6103988a8286016101ba565b6020830152506040830135828111156103b057600080fd5b6103bc8a8286016101ba565b6040830152506060830135828111156103d457600080fd5b6103e08a8286016101ba565b6060830152506080830135828111156103f857600080fd5b6104048a8286016101ba565b6080830152509350606087013591508082111561042057600080fd5b5061042d87828801610247565b91505092959194509250565b600181811c9082168061044d57607f821691505b60208210810361046d57634e487b7160e01b600052602260045260246000fd5b50919050565b601f8211156104bd57600081815260208120601f850160051c8101602086101561049a5750805b601f850160051c820191505b818110156104b9578281556001016104a6565b5050505b505050565b815167ffffffffffffffff8111156104dc576104dc61017b565b6104f0816104ea8454610439565b84610473565b602080601f831160018114610525576000841561050d5750858301515b600019600386901b1c1916600185901b1785556104b9565b600085815260208120601f198616915b8281101561055457888601518255948401946001909101908401610535565b50858210156105725787850151600019600388901b60f8161c191681555b5050505050600190811b0190555056fea26469706673582212201e34c28ec55e452e81b119401b6aaf7e84ca70ae8e57a817ca0dd0dc482bd1ba64736f6c63430008110033";

    public static final String FUNC_INSERTACCESSO = "insertAccesso";

    @Deprecated
    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> insertAccesso(String indirizzoSito, String indirizzoUtente, InfoAccessoSito infoAccessoSito, InfoAccessoUtente infoAccessoUtente) {
        final Function function = new Function(
                FUNC_INSERTACCESSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoSito), 
                new org.web3j.abi.datatypes.Address(160, indirizzoUtente), 
                infoAccessoSito, 
                infoAccessoUtente), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class InfoAccessoSito extends DynamicStruct {
        public String oraLogin;

        public String oraLogout;

        public String usernameUtente;

        public String userAgentUtente;

        public String indirizzoIPUtente;

        public InfoAccessoSito(String oraLogin, String oraLogout, String usernameUtente, String userAgentUtente, String indirizzoIPUtente) {
            super(new org.web3j.abi.datatypes.Utf8String(oraLogin), 
                    new org.web3j.abi.datatypes.Utf8String(oraLogout), 
                    new org.web3j.abi.datatypes.Utf8String(usernameUtente), 
                    new org.web3j.abi.datatypes.Utf8String(userAgentUtente), 
                    new org.web3j.abi.datatypes.Utf8String(indirizzoIPUtente));
            this.oraLogin = oraLogin;
            this.oraLogout = oraLogout;
            this.usernameUtente = usernameUtente;
            this.userAgentUtente = userAgentUtente;
            this.indirizzoIPUtente = indirizzoIPUtente;
        }

        public InfoAccessoSito(Utf8String oraLogin, Utf8String oraLogout, Utf8String usernameUtente, Utf8String userAgentUtente, Utf8String indirizzoIPUtente) {
            super(oraLogin, oraLogout, usernameUtente, userAgentUtente, indirizzoIPUtente);
            this.oraLogin = oraLogin.getValue();
            this.oraLogout = oraLogout.getValue();
            this.usernameUtente = usernameUtente.getValue();
            this.userAgentUtente = userAgentUtente.getValue();
            this.indirizzoIPUtente = indirizzoIPUtente.getValue();
        }
    }

    public static class InfoAccessoUtente extends DynamicStruct {
        public String urlSito;

        public String oraLogin;

        public String oraLogout;

        public InfoAccessoUtente(String urlSito, String oraLogin, String oraLogout) {
            super(new org.web3j.abi.datatypes.Utf8String(urlSito), 
                    new org.web3j.abi.datatypes.Utf8String(oraLogin), 
                    new org.web3j.abi.datatypes.Utf8String(oraLogout));
            this.urlSito = urlSito;
            this.oraLogin = oraLogin;
            this.oraLogout = oraLogout;
        }

        public InfoAccessoUtente(Utf8String urlSito, Utf8String oraLogin, Utf8String oraLogout) {
            super(urlSito, oraLogin, oraLogout);
            this.urlSito = urlSito.getValue();
            this.oraLogin = oraLogin.getValue();
            this.oraLogout = oraLogout.getValue();
        }
    }
}
